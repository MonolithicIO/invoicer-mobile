package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.snackbar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.snackbar.props.InkSnackBarHostState
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.snackbar.props.InkSnackBarVisuals


/**
 * Material3 SnackBarHost implementation that uses Ink design system styles.
 * It automatically handles the display duration based on the snackBar's duration setting
 * and whether it has an action button, taking accessibility settings into account.
 *
 * @param state The [InkSnackBarHostState] that holds the state of the snackBar host.
 * @param modifier The [Modifier] to be applied to this snackBar host.
 * */
@Composable
fun InkSnackBarHost(
    state: InkSnackBarHostState,
    modifier: Modifier = Modifier,
) {
    val internalState = state.internalState

    SnackbarHost(
        modifier = modifier,
        hostState = internalState,
        snackbar = { data ->
            val leadingIcon = remember(data) {
                when (val visuals = data.visuals) {
                    is InkSnackBarVisuals -> visuals.leadingIcon
                    else -> null
                }
            }

            InkSnackBar(
                modifier = Modifier.fillMaxWidth(),
                text = data.visuals.message,
                leadingIcon = leadingIcon
            )
        }
    )
}
