package io.github.monolithic.invoicer.features.auth.presentation.screens.forgotpassword.otp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import invoicer.multiplatform.features.auth.generated.resources.Res
import invoicer.multiplatform.features.auth.generated.resources.forgot_password_otp_cta
import invoicer.multiplatform.features.auth.generated.resources.forgot_password_otp_subtitle
import invoicer.multiplatform.features.auth.generated.resources.forgot_password_otp_title
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.SpacerSize
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.VerticalSpacer
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.InkPrimaryButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.scaffold.InkScaffold
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.topbar.InkTopBar
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.Title
import org.jetbrains.compose.resources.stringResource

internal class ForgotPasswordOtpScreen(
    private val forgotPasswordRequestId: String
) : Screen {

    @Composable
    override fun Content() {
        TODO("Not yet implemented")
    }

    @Composable
    fun StateContent(
        state: ForgotPasswordOtpState,
        actions: Actions
    ) {
        InkScaffold(
            topBar = {
                InkTopBar(
                    onNavigationClick = actions.onBack
                )
            },
            bottomBar = {
                InkPrimaryButton(
                    text = stringResource(Res.string.forgot_password_otp_cta),
                    onClick = actions.onSubmit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(InkTheme.spacing.medium)
                )
            },
            modifier = Modifier.imePadding()
        ) { scaffoldPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(scaffoldPadding)
                    .padding(InkTheme.spacing.medium)
                    .verticalScroll(rememberScrollState())
            ) {
                Title(
                    title = stringResource(Res.string.forgot_password_otp_title),
                    subtitle = stringResource(Res.string.forgot_password_otp_subtitle)
                )
                VerticalSpacer(SpacerSize.XLarge3)
            }
        }
    }

    data class Actions(
        val onBack: () -> Unit,
        val onSubmit: () -> Unit
    )

}