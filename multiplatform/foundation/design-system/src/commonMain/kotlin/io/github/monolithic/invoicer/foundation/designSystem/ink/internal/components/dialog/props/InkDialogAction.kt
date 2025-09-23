package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.dialog.props

data class InkDialogAction(
    val title: String,
    val onClick: () -> Unit,
    val isEnabled: Boolean = true
)
