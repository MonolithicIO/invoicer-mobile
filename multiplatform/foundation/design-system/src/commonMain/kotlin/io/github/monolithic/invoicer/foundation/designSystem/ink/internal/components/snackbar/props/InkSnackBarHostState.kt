package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.snackbar.props

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.painter.Painter

@Stable
class InkSnackBarHostState {
    internal val internalState: SnackbarHostState = SnackbarHostState()

    suspend fun showSnackBar(
        message: String,
        leadingIcon: Painter? = null,
        actionLabel: String? = null,
        withDismissAction: Boolean = false,
        duration: InkSnackBarDuration =
            if (actionLabel == null) InkSnackBarDuration.Short else InkSnackBarDuration.Indefinite
    ): InkSnackBarResult {

        return InkSnackBarResult.fromMaterialResult(
            internalState.showSnackbar(
                visuals = InkSnackBarVisuals(
                    message = message,
                    actionLabel = actionLabel,
                    duration = duration.toMaterialDuration(),
                    withDismissAction = withDismissAction,
                    leadingIcon = leadingIcon
                )
            )
        )
    }
}

enum class InkSnackBarResult {
    Dismissed, ActionPerformed;

    internal companion object {
        fun fromMaterialResult(result: SnackbarResult): InkSnackBarResult {
            return when (result) {
                SnackbarResult.Dismissed -> Dismissed
                SnackbarResult.ActionPerformed -> ActionPerformed
            }
        }
    }
}

enum class InkSnackBarDuration {
    Short, Long, Indefinite;

    internal fun toMaterialDuration(): SnackbarDuration {
        return when (this) {
            Short -> SnackbarDuration.Short
            Long -> SnackbarDuration.Long
            Indefinite -> SnackbarDuration.Indefinite
        }
    }
}

data class InkSnackBarVisuals(
    val leadingIcon: Painter?,
    override val actionLabel: String?,
    override val duration: SnackbarDuration,
    override val message: String,
    override val withDismissAction: Boolean
) : SnackbarVisuals

@Composable
fun rememberInkSnackBarHostState(): InkSnackBarHostState {
    return remember { InkSnackBarHostState() }
}
