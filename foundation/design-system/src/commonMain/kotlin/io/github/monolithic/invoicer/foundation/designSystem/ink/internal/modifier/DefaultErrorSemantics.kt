package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.modifier

import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics

internal fun Modifier.defaultErrorSemantics(
    isError: Boolean,
    defaultErrorMessage: String,
): Modifier = if (isError) semantics { error(defaultErrorMessage) } else this
