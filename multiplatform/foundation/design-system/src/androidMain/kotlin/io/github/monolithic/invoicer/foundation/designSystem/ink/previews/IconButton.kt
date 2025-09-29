package io.github.monolithic.invoicer.foundation.designSystem.ink.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import invoicer.multiplatform.foundation.design_system.generated.resources.DsResources
import invoicer.multiplatform.foundation.design_system.generated.resources.ic_settings
import io.github.monolithic.invoicer.foundation.designSystem.ink.PreviewContainer
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.icon.InkIconButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.icon.basic.InkIconButtonDefaults
import org.jetbrains.compose.resources.painterResource

@Composable
@Preview
private fun InkIconButtonPreview() {
    PreviewContainer {
        InkIconButton(
            onClick = {},
            icon = painterResource(DsResources.drawable.ic_settings),
            colors = InkIconButtonDefaults.colors.copy(
                containerColor = Color.Red,
                iconColor = Color.Blue
            )
        )
    }
}