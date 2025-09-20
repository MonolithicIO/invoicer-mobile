package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkText
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkTextStyle
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.SpacerSize
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.VerticalSpacer
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.InkPrimaryButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.InkSecondaryButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.dialog.props.InkDialogAction
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme

@Composable
fun InkDialog(
    onDismissRequest: () -> Unit,
    settings: DialogProperties = DialogProperties(),
    title: String,
    description: String,
    image: Painter? = null,
    primaryAction: InkDialogAction,
    secondaryAction: InkDialogAction? = null
) {

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = settings
    ) {
        InkDialogContent(
            title = title,
            description = description,
            image = image,
            primaryAction = primaryAction,
            secondaryAction = secondaryAction
        )
    }
}

@Composable
internal fun InkDialogContent(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    image: Painter? = null,
    primaryAction: InkDialogAction,
    secondaryAction: InkDialogAction? = null
) {
    Surface(
        modifier = modifier,
        color = InkTheme.colorScheme.background,
        shape = InkTheme.shape.large
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(InkTheme.spacing.xLarge3),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            image?.let {
                Image(
                    painter = it,
                    contentDescription = null
                )
                VerticalSpacer(height = SpacerSize.XLarge3)
            }
            InkText(
                text = title,
                style = InkTextStyle.Heading4,
                weight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            VerticalSpacer(height = SpacerSize.Medium)

            InkText(
                text = description,
                style = InkTextStyle.BodyLarge,
                textAlign = TextAlign.Center,
                weight = FontWeight.Medium
            )

            VerticalSpacer(height = SpacerSize.XLarge3)

            InkPrimaryButton(
                text = primaryAction.title,
                onClick = primaryAction.onClick,
                enabled = primaryAction.isEnabled,
                modifier = Modifier.fillMaxWidth()
            )

            secondaryAction?.let { action ->
                VerticalSpacer(height = SpacerSize.Small)
                InkSecondaryButton(
                    text = action.title,
                    onClick = action.onClick,
                    enabled = action.isEnabled,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}
