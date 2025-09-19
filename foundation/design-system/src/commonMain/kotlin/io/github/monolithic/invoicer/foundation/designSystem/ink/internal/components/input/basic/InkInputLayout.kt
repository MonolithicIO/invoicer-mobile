package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.input.basic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.offset
import androidx.compose.ui.util.fastFirstOrNull
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import kotlin.math.min
import kotlin.math.roundToInt

@Composable
internal fun InkInputLayout(
    isSingleLine: Boolean,
    paddingValues: PaddingValues,
    textField: @Composable () -> Unit,
    container: @Composable () -> Unit,
    leading: @Composable (() -> Unit)?,
    trailing: @Composable (() -> Unit)?,
    placeholder: @Composable (() -> Unit)?,
    label: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    val layoutDirection = LocalLayoutDirection.current

    Layout(
        modifier = modifier.background(Color.Yellow),
        measurePolicy = InkInputMeasurePolicy(
            singleLine = isSingleLine,
            paddingValues = paddingValues
        ),
        content = {
            container()

            val startTextFieldPadding = paddingValues.calculateStartPadding(layoutDirection)
            val endTextFieldPadding = paddingValues.calculateEndPadding(layoutDirection)

            val startPadding =
                if (leading != null) {
                    (startTextFieldPadding - InkTheme.spacing.small).coerceAtLeast(0.dp)
                } else {
                    startTextFieldPadding
                }
            val endPadding =
                if (trailing != null) {
                    (endTextFieldPadding - InkTheme.spacing.small).coerceAtLeast(0.dp)
                } else {
                    endTextFieldPadding
                }

            val textPadding =
                Modifier.heightIn(min = InkInputLayoutDefaults.MinTextLineHeight)
                    .wrapContentHeight()
                    .padding(
                        start = if (leading == null) startPadding else 0.dp,
                        end = if (trailing == null) endPadding else 0.dp,
                    )

            leading?.let {
                Box(
                    modifier = Modifier
                        .layoutId(InkInputLayoutDefaults.LeadingId)
                        .then(SideContentDefaultModifier),
                    contentAlignment = Alignment.Center
                ) {
                    it()
                }
            }

            trailing?.let {
                Box(
                    modifier = Modifier
                        .layoutId(InkInputLayoutDefaults.TrailingId)
                        .then(SideContentDefaultModifier),
                    contentAlignment = Alignment.Center
                ) {
                    it()
                }
            }

            Box(
                modifier = Modifier
                    .layoutId(InkInputLayoutDefaults.TextFieldIdId)
                    .then(textPadding),
                propagateMinConstraints = true
            ) {
                textField()
            }

            placeholder?.let {
                Box(
                    modifier = Modifier
                        .layoutId(InkInputLayoutDefaults.PlaceholderId)
                        .then(textPadding),
                ) {
                    it()
                }
            }

            label?.let {
                Box(
                    modifier = Modifier
                        .layoutId(InkInputLayoutDefaults.LabelId)
                        .background(Color.Blue)
                        .wrapContentHeight(),
                ) {
                    it()
                }
            }
        },
    )
}

private val SideContentDefaultModifier =
    Modifier.defaultMinSize(minWidth = 48.dp, minHeight = 48.dp)

internal object InkInputLayoutDefaults {
    val LeadingId = "Leading"
    val TrailingId = "Trailing"
    val PlaceholderId = "Placeholder"
    val TextFieldIdId = "TextField"
    val ContainerId = "Container"
    val LabelId = "Label"

    val MinTextLineHeight = 24.dp
}

private class InkInputMeasurePolicy(
    private val singleLine: Boolean,
    private val paddingValues: PaddingValues
) : MeasurePolicy {

    override fun MeasureScope.measure(
        measurables: List<Measurable>,
        constraints: Constraints
    ): MeasureResult {
        val topPadding = paddingValues.calculateTopPadding().roundToPx()
        val bottomPadding = paddingValues.calculateBottomPadding().roundToPx()
        val looseConstraints = constraints.copy(minWidth = 0, minHeight = 0)

        var horizontalSpace = 0

        val leading =
            measurables
                .fastFirstOrNull { it.layoutId == InkInputLayoutDefaults.LeadingId }
                ?.measure(looseConstraints)

        horizontalSpace += leading?.width ?: 0

        val trailing =
            measurables
                .fastFirstOrNull { it.layoutId == InkInputLayoutDefaults.TrailingId }
                ?.measure(looseConstraints)
        horizontalSpace += trailing?.width ?: 0


        val textFieldConstraints =
            constraints
                .offset(
                    horizontal = -horizontalSpace,
                    vertical = -bottomPadding - topPadding
                )
                .copy(minHeight = 0)

        val label =
            measurables.fastFirstOrNull { it.layoutId == InkInputLayoutDefaults.LabelId }
                ?.measure(looseConstraints)

        val textField =
            measurables.fastFirstOrNull { it.layoutId == InkInputLayoutDefaults.TextFieldIdId }
                ?.measure(textFieldConstraints)


        val placeholderConstraints = textFieldConstraints.copy(minWidth = 0)
        val placeHolder =
            measurables.fastFirstOrNull { it.layoutId == InkInputLayoutDefaults.PlaceholderId }
                ?.measure(placeholderConstraints)

        val componentHeight = calculateHeight(
            leadingHeight = leading?.height ?: 0,
            trailingHeight = trailing?.height ?: 0,
            textFieldHeight = textField?.height ?: 0,
            labelHeight = label?.height ?: 0,
            placeholderHeight = placeHolder?.height ?: 0,
            constraints = constraints,
            density = density,
            paddingValues = paddingValues,
        )

        val componentWidth = calculateWidth(
            leadingPlaceableWidth = leading?.width ?: 0,
            trailingPlaceableWidth = trailing?.width ?: 0,
            textFieldPlaceableWidth = textField?.width ?: 0,
            placeholderPlaceableWidth = placeHolder?.width ?: 0,
            labelWidth = label?.width ?: 0,
            constraints = constraints
        )

        val containerHeight = componentHeight - (label?.height ?: 0)

        val container =
            measurables.fastFirstOrNull { it.layoutId == InkInputLayoutDefaults.ContainerId }
                ?.measure(
                    Constraints(
                        minWidth = if (componentWidth != Constraints.Infinity) componentWidth else 0,
                        maxWidth = componentWidth,
                        minHeight = if (containerHeight != Constraints.Infinity) containerHeight else 0,
                        maxHeight = containerHeight
                    )
                )

        return layout(
            componentWidth, componentHeight
        ) {
            container?.place(IntOffset.Zero)

            label?.placeRelative(
                x = 0,
                y = 0
            )

            leading?.let { leadingItem ->
                leadingItem.placeRelative(
                    x = 0,
                    y = Alignment.CenterVertically.align(
                        size = leadingItem.height,
                        space = containerHeight
                    )
                )
            }

            val verticalTextFieldPosition = if (singleLine) {
                Alignment.CenterVertically.align(
                    size = textField?.height ?: 0,
                    space = containerHeight
                )
            } else {
                (paddingValues.calculateTopPadding() * density).value.roundToInt()
            }

            textField?.placeRelative(
                x = leading?.width ?: 0,
                y = verticalTextFieldPosition
            )

            placeHolder?.placeRelative(
                x = leading?.width ?: 0,
                y = verticalTextFieldPosition
            )


            trailing?.let { trailingItem ->
                trailingItem.placeRelative(
                    x = (componentWidth - (trailingItem.width)),
                    y = Alignment.CenterVertically.align(
                        size = trailingItem.height,
                        space = containerHeight
                    )
                )
            }
        }
    }

    private fun calculateHeight(
        leadingHeight: Int,
        trailingHeight: Int,
        placeholderHeight: Int,
        textFieldHeight: Int,
        labelHeight: Int,
        paddingValues: PaddingValues,
        constraints: Constraints,
        density: Float
    ): Int {
        val textFieldHeight =
            maxOf(textFieldHeight, leadingHeight, trailingHeight)

        val placeHolderHeight =
            maxOf(placeholderHeight, leadingHeight, trailingHeight)

        val textContentHeight = maxOf(textFieldHeight, placeHolderHeight)
        val bottomPadding = paddingValues.calculateBottomPadding().value * density
        val topPadding = paddingValues.calculateTopPadding().value * density

        val contentHeight =
            (textContentHeight + topPadding + bottomPadding).roundToInt()

        return maxOf(
            contentHeight + labelHeight,
            constraints.minHeight
        )
    }

    private fun calculateWidth(
        leadingPlaceableWidth: Int,
        trailingPlaceableWidth: Int,
        textFieldPlaceableWidth: Int,
        placeholderPlaceableWidth: Int,
        labelWidth: Int,
        constraints: Constraints,
    ): Int {
        val sideContentWidth = leadingPlaceableWidth + trailingPlaceableWidth
        val middleSection =
            maxOf(
                textFieldPlaceableWidth + sideContentWidth,
                placeholderPlaceableWidth + sideContentWidth
            )

        val wrappedWidth = middleSection + labelWidth


        return min(wrappedWidth, constraints.maxWidth)
    }
}
