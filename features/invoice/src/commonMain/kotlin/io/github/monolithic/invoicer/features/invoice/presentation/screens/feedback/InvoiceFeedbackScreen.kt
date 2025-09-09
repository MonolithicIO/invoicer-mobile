package io.github.monolithic.invoicer.features.invoice.presentation.screens.feedback

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.annotation.InternalVoyagerApi
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.internal.BackHandler
import invoicer.features.invoice.generated.resources.Res
import invoicer.features.invoice.generated.resources.invoice_feedback_cta
import invoicer.features.invoice.generated.resources.invoice_feedback_description
import invoicer.features.invoice.generated.resources.invoice_feedback_title
import io.github.monolithic.invoicer.foundation.designSystem.components.feedback.Feedback
import io.github.monolithic.invoicer.foundation.designSystem.legacy.tokens.Spacing
import org.jetbrains.compose.resources.stringResource

internal class InvoiceFeedbackScreen : Screen {

    @OptIn(InternalVoyagerApi::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current?.parent

        BackHandler(true) {
            // no-op: Disable back button
        }

        StateContent(
            onClearFlow = {
                navigator?.pop()
            }
        )
    }

    @Composable
    fun StateContent(
        onClearFlow: () -> Unit
    ) {
        Scaffold(
            modifier = Modifier.systemBarsPadding()
        ) {
            Feedback(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(Spacing.medium)
                    .padding(it),
                primaryActionText = stringResource(Res.string.invoice_feedback_cta),
                title = stringResource(Res.string.invoice_feedback_title),
                description = stringResource(Res.string.invoice_feedback_description),
                onPrimaryAction = {
                    onClearFlow()
                },
                icon = Icons.Outlined.Check
            )
        }
    }
}
