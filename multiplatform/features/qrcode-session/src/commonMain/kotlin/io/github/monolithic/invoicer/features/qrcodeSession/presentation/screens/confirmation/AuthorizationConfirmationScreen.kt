package io.github.monolithic.invoicer.features.qrcodeSession.presentation.screens.confirmation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import invoicer.multiplatform.features.qrcode_session.generated.resources.Res
import invoicer.multiplatform.features.qrcode_session.generated.resources.qr_code_details
import invoicer.multiplatform.features.qrcode_session.generated.resources.qr_code_details_error_description
import invoicer.multiplatform.features.qrcode_session.generated.resources.qr_code_details_error_primary_cta
import invoicer.multiplatform.features.qrcode_session.generated.resources.qr_code_details_error_secondary_cta
import invoicer.multiplatform.features.qrcode_session.generated.resources.qr_code_details_error_title
import io.github.monolithic.invoicer.features.qrcodeSession.presentation.screens.confirmation.components.CodeDetails
import io.github.monolithic.invoicer.features.qrcodeSession.presentation.screens.success.AuthorizationSuccessScreen
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.scaffold.InkScaffold
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.topbar.InkTopBar
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.ErrorState
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.ErrorStateAction
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.LoadingState
import io.github.monolithic.invoicer.foundation.utils.compose.FlowCollectEffect
import org.jetbrains.compose.resources.stringResource

internal data class AuthorizationConfirmationScreen(
    private val codeContentId: String
) : Screen {

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<AuthorizationConfirmationScreenModel>()
        val state = screenModel.state.collectAsState()
        val navigator = LocalNavigator.current

        LaunchedEffect(Unit) {
            screenModel.getCodeDetails(codeContentId)
        }

        FlowCollectEffect(flow = screenModel.events, key = screenModel) { event ->
            when (event) {
                AuthorizationConfirmationEvents.Authorized -> navigator?.push(
                    AuthorizationSuccessScreen()
                )
            }
        }

        StateContent(
            state = state.value,
            onBack = { navigator?.pop() },
            onAuthorize = {
                screenModel.authorizeQrCode(codeContentId)
            },
            onRetryLoadData = {
                screenModel.getCodeDetails(codeContentId)
            }
        )
    }

    @Composable
    fun StateContent(
        state: AuthorizationConfirmationState,
        onBack: () -> Unit,
        onAuthorize: () -> Unit,
        onRetryLoadData: () -> Unit,
    ) {
        InkScaffold(
            topBar = {
                InkTopBar(
                    title = stringResource(Res.string.qr_code_details),
                    onNavigationClick = onBack,
                    modifier = Modifier.statusBarsPadding()
                )
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(InkTheme.spacing.medium)
                    .navigationBarsPadding()
            ) {
                when (state.mode) {
                    AuthorizationConfirmationMode.Content -> CodeDetails(
                        modifier = Modifier.fillMaxSize(),
                        qrCodeAgent = state.qrCodeAgent,
                        qrCodeIp = state.qrCodeIp,
                        qrCodeExpiration = state.qrCodeExpiration,
                        qrCodeEmission = state.qrCodeEmission,
                        onAuthorize = onAuthorize
                    )

                    AuthorizationConfirmationMode.Error -> ErrorState(
                        title = stringResource(Res.string.qr_code_details_error_title),
                        description = stringResource(Res.string.qr_code_details_error_description),
                        modifier = Modifier.fillMaxSize(),
                        primaryAction = ErrorStateAction(
                            label = stringResource(Res.string.qr_code_details_error_primary_cta),
                            action = onRetryLoadData
                        ),
                        secondaryAction =
                            ErrorStateAction(
                                label = stringResource(Res.string.qr_code_details_error_secondary_cta),
                                action = onBack
                            )
                    )

                    AuthorizationConfirmationMode.AuthorizeError ->
                        ErrorState(
                            title = stringResource(Res.string.qr_code_details_error_title),
                            description = stringResource(Res.string.qr_code_details_error_description),
                            modifier = Modifier.fillMaxSize(),
                            primaryAction = ErrorStateAction(
                                label = stringResource(Res.string.qr_code_details_error_primary_cta),
                                action = onAuthorize
                            ),
                            secondaryAction =
                                ErrorStateAction(
                                    label = stringResource(Res.string.qr_code_details_error_secondary_cta),
                                    action = onBack
                                )
                        )


                    AuthorizationConfirmationMode.Loading -> LoadingState(
                        Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    )
                }
            }
        }
    }

}
