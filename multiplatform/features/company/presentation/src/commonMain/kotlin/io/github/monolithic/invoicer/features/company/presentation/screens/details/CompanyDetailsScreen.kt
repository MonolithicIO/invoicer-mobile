package io.github.monolithic.invoicer.features.company.presentation.screens.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import invoicer.multiplatform.features.company.presentation.generated.resources.Res
import invoicer.multiplatform.features.company.presentation.generated.resources.company_details_title
import invoicer.multiplatform.features.company.presentation.generated.resources.company_error_retry
import io.github.monolithic.invoicer.features.company.presentation.model.CompanyPaymentUiModel
import io.github.monolithic.invoicer.features.company.presentation.screens.details.components.CompanyDetailsContent
import io.github.monolithic.invoicer.features.company.presentation.screens.updateaddress.UpdateAddressScreen
import io.github.monolithic.invoicer.features.company.presentation.screens.updatepayaccount.UpdatePayAccountScreen
import io.github.monolithic.invoicer.features.company.presentation.screens.updatepayaccount.UpdatePayAccountScreenArgs
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.scaffold.InkScaffold
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.topbar.InkTopBar
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.ErrorState
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.ErrorStateAction
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.LoadingState
import org.jetbrains.compose.resources.stringResource

internal class CompanyDetailsScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<CompanyDetailsScreenModel>()
        val state by screenModel.state.collectAsState()
        val navigator = LocalNavigator.current

        LaunchedEffect(screenModel) {
            screenModel.initState()
        }

        StateContent(
            state = state,
            actions = remember {
                Actions(
                    onBack = { navigator?.pop() },
                    onEditIntermediaryAccount = { account ->
                        navigator?.push(
                            UpdatePayAccountScreen(
                                args = UpdatePayAccountScreenArgs(
                                    payAccountId = account.id,
                                    swift = account.swift,
                                    iban = account.iban,
                                    bankAddress = account.bankAddress,
                                    bankName = account.bankName,
                                    accountType = UpdatePayAccountScreenArgs.AccountType.Intermediary
                                )
                            )
                        )
                    },
                    onEditPayAccount = { account ->
                        navigator?.push(
                            UpdatePayAccountScreen(
                                args = UpdatePayAccountScreenArgs(
                                    payAccountId = account.id,
                                    swift = account.swift,
                                    iban = account.iban,
                                    bankAddress = account.bankAddress,
                                    bankName = account.bankName,
                                    accountType = UpdatePayAccountScreenArgs.AccountType.Primary
                                )
                            )
                        )
                    },
                    onEditAddress = {
                        navigator?.push(
                            UpdateAddressScreen(
                                args = UpdateAddressScreen.Args(
                                    addressLine = state.addressLine1,
                                    addressLine2 = state.addressLine2,
                                    city = state.city,
                                    state = state.state,
                                    postalCode = state.postalCode,
                                )
                            )
                        )
                    },
                    onRetry = screenModel::initState
                )
            }
        )
    }

    @Composable
    fun StateContent(
        state: CompanyDetailsState,
        actions: Actions
    ) {
        val scroll = rememberScrollState()
        InkScaffold(
            topBar = {
                InkTopBar(
                    onNavigationClick = actions.onBack,
                    title = stringResource(Res.string.company_details_title),
                    modifier = Modifier.statusBarsPadding()
                )
            }
        ) { scaffoldPadding ->
            Column(
                modifier = Modifier
                    .padding(scaffoldPadding)
                    .padding(InkTheme.spacing.medium)
                    .fillMaxSize()
            ) {
                when (state.mode) {
                    CompanyDetailsMode.Loading -> LoadingState(Modifier.fillMaxSize())

                    CompanyDetailsMode.Content ->
                        CompanyDetailsContent(
                            modifier = Modifier
                                .fillMaxSize()
                                .verticalScroll(scroll)
                                .navigationBarsPadding(),
                            state = state,
                            onEditAddress = actions.onEditAddress,
                            onEditPrimaryAccount = { actions.onEditPayAccount(state.payAccount) },
                            onEditIntermediaryAccount = {
                                state.intermediaryAccount?.let {
                                    actions.onEditIntermediaryAccount(
                                        it
                                    )
                                }
                            }
                        )

                    CompanyDetailsMode.Error -> ErrorState(
                        modifier = Modifier.fillMaxSize(),
                        primaryAction = ErrorStateAction(
                            action = actions.onRetry,
                            label = stringResource(Res.string.company_error_retry)
                        )
                    )
                }
            }
        }
    }

    data class Actions(
        val onBack: () -> Unit,
        val onEditPayAccount: (CompanyPaymentUiModel) -> Unit,
        val onEditIntermediaryAccount: (CompanyPaymentUiModel) -> Unit,
        val onEditAddress: () -> Unit,
        val onRetry: () -> Unit,
    )
}
