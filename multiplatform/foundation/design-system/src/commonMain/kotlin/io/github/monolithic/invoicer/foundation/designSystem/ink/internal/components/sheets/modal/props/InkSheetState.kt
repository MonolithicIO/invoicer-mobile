package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.sheets.modal.props

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density

class InkSheetState(
    private val skipPartiallyExpanded: Boolean,
    private val density: Density,
    private val initialValue: InkSheetValue,
    private val confirmValueChange: (InkSheetValue) -> Boolean,
    private val skipHiddenState: Boolean
) {

    @OptIn(ExperimentalMaterial3Api::class)
    internal val internalSheetState = SheetState(
        skipPartiallyExpanded = skipPartiallyExpanded,
        density = density,
        initialValue = initialValue.toSheetValue(),
        confirmValueChange = { sheetValue ->
            confirmValueChange(InkSheetValue.fromSheetValue(sheetValue))
        },
        skipHiddenState = skipHiddenState
    )

    @OptIn(ExperimentalMaterial3Api::class)
    suspend fun hide() {
        internalSheetState.hide()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    suspend fun show() {
        internalSheetState.show()
    }
}

enum class InkSheetValue {
    Hidden,
    Expanded,
    PartiallyExpanded;

    @OptIn(ExperimentalMaterial3Api::class)
    internal fun toSheetValue(): SheetValue {
        return when (this) {
            Hidden -> SheetValue.Hidden
            Expanded -> SheetValue.Expanded
            PartiallyExpanded -> SheetValue.PartiallyExpanded
        }
    }

    internal companion object {
        @OptIn(ExperimentalMaterial3Api::class)
        fun fromSheetValue(sheetValue: SheetValue): InkSheetValue {
            return when (sheetValue) {
                SheetValue.Hidden -> Hidden
                SheetValue.Expanded -> Expanded
                SheetValue.PartiallyExpanded -> PartiallyExpanded
            }
        }
    }
}

@Composable
fun rememberInkBottomSheetState(
    skipPartiallyExpanded: Boolean = false,
    confirmValueChange: (InkSheetValue) -> Boolean = { true },
): InkSheetState {
    val density = LocalDensity.current.density

    return remember {
        InkSheetState(
            skipPartiallyExpanded = skipPartiallyExpanded,
            confirmValueChange = confirmValueChange,
            density = Density(density),
            initialValue = InkSheetValue.Hidden,
            skipHiddenState = false
        )
    }
}
