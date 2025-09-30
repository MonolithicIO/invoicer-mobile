package io.github.monolithic.invoicer.features.invoice.presentation.screens.create.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastFirstOrNull
import invoicer.multiplatform.features.invoice.presentation.generated.resources.Res
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_create_step
import invoicer.multiplatform.foundation.design_system.generated.resources.DsResources
import invoicer.multiplatform.foundation.design_system.generated.resources.ic_chveron_left
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkHorizontalIndicator
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkText
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkTextStyle
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.icon.InkIconButton
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import kotlin.math.max
import kotlin.math.roundToInt

private val MinTopBarHeight = 72.dp
private const val MaxStep = 4

@Composable
internal fun CreateInvoiceToolbar(
    onBack: () -> Unit,
    step: Int,
) {
    CreateInvoiceBasicToolbar(
        leading = {
            InkIconButton(
                onClick = onBack,
                icon = painterResource(DsResources.drawable.ic_chveron_left)
            )
        },
        middle = {
            InkHorizontalIndicator(
                progress = (step.toFloat() / MaxStep.toFloat())
            )
        },
        trailing = {
            InkText(
                text = stringResource(
                    Res.string.invoice_create_step, step, MaxStep
                ),
                style = InkTextStyle.Heading5,
                weight = FontWeight.SemiBold
            )
        }
    )
}

@Composable
private fun CreateInvoiceBasicToolbar(
    leading: @Composable () -> Unit,
    middle: @Composable () -> Unit,
    trailing: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Layout(
        measurePolicy = InkBasicToolbarMeasurePolicy(),
        modifier = modifier.defaultMinSize(minHeight = MinTopBarHeight),
        content = {
            Box(
                modifier = Modifier.layoutId(InkBasicToolbarDefaults.LeadingId),
                contentAlignment = Alignment.Center
            ) { leading() }
            Box(
                modifier = Modifier.layoutId(InkBasicToolbarDefaults.MiddleId),
                contentAlignment = Alignment.Center
            ) { middle() }
            Box(
                modifier = Modifier.layoutId(InkBasicToolbarDefaults.TrailingId),
                contentAlignment = Alignment.Center
            ) { trailing() }
        }
    )
}

private object InkBasicToolbarDefaults {
    const val LeadingId = "Leading"
    const val MiddleId = "Middle"
    const val TrailingId = "Trailing"
    val ItemSpacing = 16.dp
}

private class InkBasicToolbarMeasurePolicy : MeasurePolicy {
    override fun MeasureScope.measure(
        measurables: List<Measurable>,
        constraints: Constraints
    ): MeasureResult {
        val itemSpacing = InkBasicToolbarDefaults.ItemSpacing.toPx().roundToInt()

        val sideContentConstraints = constraints.copy(
            minWidth = (constraints.maxWidth * 0.2).roundToInt() - itemSpacing,
            minHeight = 0,
            maxWidth = (constraints.maxWidth * 0.2).roundToInt() - itemSpacing
        )

        val mainContentConstraints = constraints.copy(
            minWidth = (constraints.maxWidth * 0.6).toInt(),
            minHeight = 0,
            maxWidth = (constraints.maxWidth * 0.6).toInt()
        )

        val leadingPlaceable = measurables
            .fastFirstOrNull { it.layoutId == InkBasicToolbarDefaults.LeadingId }
            ?.measure(sideContentConstraints)

        val middlePlaceable =
            measurables
                .fastFirstOrNull { it.layoutId == InkBasicToolbarDefaults.MiddleId }
                ?.measure(mainContentConstraints)

        val trailingPlaceable =
            measurables
                .fastFirstOrNull { it.layoutId == InkBasicToolbarDefaults.TrailingId }
                ?.measure(sideContentConstraints)

        val componentHeight = calculateHeight(
            trailingHeight = trailingPlaceable.heightOrZero(),
            middleHeight = middlePlaceable.heightOrZero(),
            leadingHeight = leadingPlaceable.heightOrZero(),
            minHeight = constraints.minHeight
        )

        return layout(
            width = constraints.maxWidth,
            height = componentHeight
        ) {
            leadingPlaceable?.placeRelative(
                x = 0,
                y = Alignment.CenterVertically.align(
                    size = leadingPlaceable.heightOrZero(),
                    space = componentHeight
                )
            )

            middlePlaceable?.placeRelative(
                x = leadingPlaceable.widthOrZero() + itemSpacing,
                y = Alignment.CenterVertically.align(
                    size = middlePlaceable.heightOrZero(),
                    space = componentHeight
                )
            )

            trailingPlaceable?.placeRelative(
                x = leadingPlaceable.widthOrZero() + middlePlaceable.widthOrZero()
                        + (itemSpacing * 2),
                y = Alignment.CenterVertically.align(
                    size = trailingPlaceable.heightOrZero(),
                    space = componentHeight
                )
            )
        }
    }

    private fun calculateHeight(
        trailingHeight: Int,
        middleHeight: Int,
        leadingHeight: Int,
        minHeight: Int,
    ): Int {
        val contentHeight = maxOf(trailingHeight, middleHeight, leadingHeight)

        return max(contentHeight, minHeight)
    }

    private fun Placeable?.widthOrZero() = this?.width ?: 0
    private fun Placeable?.heightOrZero() = this?.height ?: 0
}