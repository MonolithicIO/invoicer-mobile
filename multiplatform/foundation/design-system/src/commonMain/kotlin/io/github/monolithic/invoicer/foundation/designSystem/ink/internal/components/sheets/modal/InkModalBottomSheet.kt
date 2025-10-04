package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.sheets.modal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.sheets.modal.props.InkModalBottomSheetConfig
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.sheets.modal.props.InkSheetState
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.sheets.modal.props.rememberInkBottomSheetState
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme

private val DefaultScrimColor = Color.Black.copy(alpha = 0.6f)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InkModalBottomSheet(
    onDismiss: () -> Unit,
    isVisible: Boolean,
    modifier: Modifier = Modifier,
    sheetState: InkSheetState = rememberInkBottomSheetState(),
    sheetMaxWidth: Dp = BottomSheetDefaults.SheetMaxWidth,
    shape: Shape = InkTheme.shape.small,
    containerColor: Color = InkTheme.colorScheme.background,
    contentColor: Color = InkTheme.colorScheme.onBackground,
    tonalElevation: Dp = 0.dp,
    scrimColor: Color = DefaultScrimColor,
    dragHandle: @Composable (() -> Unit)? = { InkSheetHandle() },
    contentWindowInsets: @Composable () -> WindowInsets = { BottomSheetDefaults.windowInsets },
    properties: InkModalBottomSheetConfig = InkModalBottomSheetConfig(),
    content: @Composable ColumnScope.() -> Unit,
) {
    if (isVisible) {
        ModalBottomSheet(
            onDismissRequest = onDismiss,
            modifier = modifier,
            sheetState = sheetState.internalSheetState,
            sheetMaxWidth = sheetMaxWidth,
            shape = shape,
            containerColor = containerColor,
            contentColor = contentColor,
            tonalElevation = tonalElevation,
            scrimColor = scrimColor,
            dragHandle = dragHandle,
            contentWindowInsets = contentWindowInsets,
            properties = properties.toModalBottomSheetProperties(),
            content = content
        )
    }
}

@Composable
private fun InkSheetHandle(
    modifier: Modifier = Modifier
) {
    Box(
        modifier
            .padding(top = InkTheme.spacing.small)
            .height(4.dp)
            .width(64.dp)
            .background(InkTheme.colorScheme.onBackgroundVariant)
    )

}
