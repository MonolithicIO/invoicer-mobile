package io.github.monolithic.invoicer.foundation.designSystem.ink.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.dialog.InkDialog
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.dialog.props.InkDialogAction
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.theme.InkTheme

private data class InkDialogPreviewParams(
    val title: String,
    val description: String,
    val primaryActionTitle: String,
    val secondaryActionTitle: String?,
)

private class InkDialogPreviewProvider : PreviewParameterProvider<InkDialogPreviewParams> {
    override val values: Sequence<InkDialogPreviewParams>
        get() = sequenceOf(
            InkDialogPreviewParams(
                title = "Dialog Title",
                description = "This is a description for the dialog. " +
                        "It provides more details about the dialog's purpose.",
                primaryActionTitle = "Confirm",
                secondaryActionTitle = "Cancel"
            ),
            InkDialogPreviewParams(
                title = "Another Dialog",
                description = "Here is another example of a dialog with different text content.",
                primaryActionTitle = "Accept",
                secondaryActionTitle = null
            )
        )
}

@Preview
@Composable
private fun InkDialogPreview(
    @PreviewParameter(InkDialogPreviewProvider::class) params: InkDialogPreviewParams
) {
    InkTheme {
        InkDialog(
            onDismissRequest = {},
            title = params.title,
            description = params.description,
            primaryAction = InkDialogAction(
                title = params.primaryActionTitle,
                onClick = {}
            ),
            secondaryAction = params.secondaryActionTitle?.let {
                InkDialogAction(
                    title = it,
                    onClick = {}
                )
            }
        )
    }
}
