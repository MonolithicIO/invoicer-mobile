package io.github.monolithic.invoicer.features.invoice.presentation.screens.feedback

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.annotation.InternalVoyagerApi
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.internal.BackHandler
import invoicer.multiplatform.features.invoice.presentation.generated.resources.Res
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_feedback_cta
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_feedback_description
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_feedback_title
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.InkPrimaryButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.scaffold.InkScaffold
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.SuccessState
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
        InkScaffold(
            bottomBar = {
                InkPrimaryButton(
                    text = stringResource(Res.string.invoice_feedback_cta),
                    onClick = onClearFlow,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(InkTheme.spacing.medium)
                        .navigationBarsPadding()
                )
            }
        ) { scaffoldPadding ->
            SuccessState(
                title = stringResource(Res.string.invoice_feedback_title),
                description = stringResource(Res.string.invoice_feedback_description),
                modifier = Modifier
                    .padding(scaffoldPadding)
                    .padding(InkTheme.spacing.medium)
                    .fillMaxSize()
            )
        }
    }
}
