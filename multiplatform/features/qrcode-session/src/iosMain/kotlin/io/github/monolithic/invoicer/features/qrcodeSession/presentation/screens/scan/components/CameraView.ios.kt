package io.github.monolithic.invoicer.features.qrcodeSession.presentation.screens.scan.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkText

@Composable
actual fun QrCodeCameraView(
    modifier: Modifier,
    onScan: (String) -> Unit,
    onFail: (Throwable) -> Unit,
) {
    InkText("I'm not ready yet XD")
}
