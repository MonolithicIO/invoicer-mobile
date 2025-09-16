package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.input.basic

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.util.fastFirstOrNull

@Composable
internal fun InkInputLayout(
    isSingleLine: Boolean,
    paddingValues: PaddingValues,
    leading: @Composable (() -> Unit)?,
    trailing: @Composable (() -> Unit)?,
    placeholder: @Composable (() -> Unit)?,
    textField: (@Composable () -> Unit)?,
    modifier: Modifier = Modifier
) {
    Layout(
        modifier = modifier,
        measurePolicy = InkInputMeasurePolicy(
            singleLine = isSingleLine,
            paddingValues = paddingValues
        ),
        content = {
            leading?.let {
                Box(
                    modifier = Modifier
                        .layoutId(InkInputLayoutDefaults.LeadingId),
                    contentAlignment = Alignment.Center
                ) {
                    it()
                }
            }
            placeholder?.let {
                Box(
                    modifier = Modifier
                        .layoutId(InkInputLayoutDefaults.PlaceholderId),
                    contentAlignment = Alignment.Center
                ) {
                    it()
                }
            }
            textField?.let {
                Box(
                    modifier = Modifier
                        .layoutId(InkInputLayoutDefaults.TextFieldIdId),
                    contentAlignment = Alignment.Center
                ) {
                    it()
                }
            }
            trailing?.let {
                Box(
                    modifier = Modifier
                        .layoutId(InkInputLayoutDefaults.TrailingId),
                    contentAlignment = Alignment.Center
                ) {
                    it()
                }
            }
        },
    )
}

private object InkInputLayoutDefaults {
    val LeadingId = "Leading"
    val TrailingId = "Trailing"
    val PlaceholderId = "Placeholder"
    val TextFieldIdId = "TextField"
}

private class InkInputMeasurePolicy(
    private val singleLine: Boolean,
    private val paddingValues: PaddingValues
) : MeasurePolicy {

    override fun MeasureScope.measure(
        measurables: List<Measurable>,
        constraints: Constraints
    ): MeasureResult {
        val leading =
            measurables.fastFirstOrNull { it.layoutId == InkInputLayoutDefaults.LeadingId }
        val trailing =
            measurables.fastFirstOrNull { it.layoutId == InkInputLayoutDefaults.TrailingId }
        val placeholder =
            measurables.fastFirstOrNull { it.layoutId == InkInputLayoutDefaults.PlaceholderId }
        val textField =
            measurables.fastFirstOrNull { it.layoutId == InkInputLayoutDefaults.TextFieldIdId }

        return layout(constraints.minWidth, constraints.minHeight) {

        }
    }
}