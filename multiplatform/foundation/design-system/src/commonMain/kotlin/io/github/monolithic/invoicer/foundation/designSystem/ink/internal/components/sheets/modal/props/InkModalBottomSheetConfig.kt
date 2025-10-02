package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.sheets.modal.props

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheetProperties

data class InkModalBottomSheetConfig(
    val dismissOnBackClick: Boolean = true
) {

    @OptIn(ExperimentalMaterial3Api::class)
    internal fun toModalBottomSheetProperties(): ModalBottomSheetProperties {
        return ModalBottomSheetProperties(
            shouldDismissOnBackPress = dismissOnBackClick
        )
    }
}
