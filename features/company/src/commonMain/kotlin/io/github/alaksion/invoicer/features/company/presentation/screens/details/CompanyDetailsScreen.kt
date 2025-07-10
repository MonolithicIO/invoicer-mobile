package io.github.alaksion.invoicer.features.company.presentation.screens.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import invoicer.features.company.generated.resources.Res
import invoicer.features.company.generated.resources.company_details_address
import invoicer.features.company.generated.resources.company_details_address_city
import invoicer.features.company.generated.resources.company_details_address_country_code
import invoicer.features.company.generated.resources.company_details_address_line1
import invoicer.features.company.generated.resources.company_details_address_line2
import invoicer.features.company.generated.resources.company_details_address_postal_code
import invoicer.features.company.generated.resources.company_details_address_state
import invoicer.features.company.generated.resources.company_details_info
import invoicer.features.company.generated.resources.company_details_info_document
import invoicer.features.company.generated.resources.company_details_info_name
import invoicer.features.company.generated.resources.company_details_pay_account
import invoicer.features.company.generated.resources.company_details_pay_account_bank_address
import invoicer.features.company.generated.resources.company_details_pay_account_bank_name
import invoicer.features.company.generated.resources.company_details_pay_account_iban
import invoicer.features.company.generated.resources.company_details_pay_account_swift
import invoicer.features.company.generated.resources.company_details_title
import io.github.alaksion.invoicer.features.company.presentation.screens.details.components.CompanyDetailsCard
import io.github.alaksion.invoicer.features.company.presentation.screens.details.components.CompanyDetailsRow
import io.github.alaksion.invoicer.foundation.designSystem.components.LoadingState
import io.github.alaksion.invoicer.foundation.designSystem.components.buttons.BackButton
import io.github.alaksion.invoicer.foundation.designSystem.components.feedback.ErrorFeedback
import io.github.alaksion.invoicer.foundation.designSystem.tokens.Spacing
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
            callbacks = remember {
                Callbacks(
                    onBack = { navigator?.pop() },
                    onEditIntermediaryAccount = { accountId ->

                    },
                    onEditPayAccount = { accountId ->

                    },
                    onEditAddress = {

                    },
                    onRetry = screenModel::initState
                )
            }
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun StateContent(
        state: CompanyDetailsState,
        callbacks: Callbacks
    ) {
        val scroll = rememberScrollState()
        Scaffold(
            modifier = Modifier.systemBarsPadding(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(Res.string.company_details_title)
                        )
                    },
                    navigationIcon = {
                        BackButton(onBackClick = callbacks.onBack)
                    }
                )
            }
        ) { scaffoldPadding ->
            Surface(
                modifier = Modifier
                    .padding(scaffoldPadding)
                    .padding(Spacing.medium)
                    .fillMaxSize()
            ) {
                when (state.mode) {
                    CompanyDetailsMode.Loading -> LoadingState(Modifier.fillMaxSize())

                    CompanyDetailsMode.Content ->
                        Column(
                            modifier = Modifier.fillMaxSize().verticalScroll(scroll),
                            verticalArrangement = Arrangement.spacedBy(Spacing.medium)
                        ) {
                            CompanyDetailsCard(
                                modifier = Modifier.fillMaxWidth(),
                                title = stringResource(Res.string.company_details_info)
                            ) {
                                CompanyDetailsRow(
                                    title = stringResource(Res.string.company_details_info_name),
                                    content = state.name
                                )
                                CompanyDetailsRow(
                                    title = stringResource(Res.string.company_details_info_document),
                                    content = state.document
                                )
                            }

                            CompanyDetailsCard(
                                modifier = Modifier.fillMaxWidth(),
                                title = stringResource(Res.string.company_details_address),
                                onEditClick = callbacks.onEditAddress
                            ) {
                                CompanyDetailsRow(
                                    title = stringResource(Res.string.company_details_address_line1),
                                    content = state.addressLine1
                                )
                                state.addressLine2?.let {
                                    CompanyDetailsRow(
                                        title = stringResource(Res.string.company_details_address_line2),
                                        content = it
                                    )
                                }
                                CompanyDetailsRow(
                                    title = stringResource(Res.string.company_details_address_state),
                                    content = state.state
                                )
                                CompanyDetailsRow(
                                    title = stringResource(Res.string.company_details_address_city),
                                    content = state.city
                                )
                                CompanyDetailsRow(
                                    title = stringResource(Res.string.company_details_address_postal_code),
                                    content = state.postalCode
                                )
                                CompanyDetailsRow(
                                    title = stringResource(Res.string.company_details_address_country_code),
                                    content = state.countryCode
                                )
                            }

                            CompanyDetailsCard(
                                modifier = Modifier.fillMaxWidth(),
                                title = stringResource(Res.string.company_details_pay_account),
                                onEditClick = {
                                    callbacks.onEditPayAccount(state.payAccount.id)
                                }
                            ) {
                                CompanyDetailsRow(
                                    title = stringResource(Res.string.company_details_pay_account_swift),
                                    content = state.payAccount.swift
                                )
                                CompanyDetailsRow(
                                    title = stringResource(Res.string.company_details_pay_account_iban),
                                    content = state.payAccount.iban
                                )
                                CompanyDetailsRow(
                                    title = stringResource(Res.string.company_details_pay_account_bank_name),
                                    content = state.payAccount.bankName
                                )
                                CompanyDetailsRow(
                                    title = stringResource(Res.string.company_details_pay_account_bank_address),
                                    content = state.payAccount.bankAddress
                                )
                            }

                            state.intermediaryAccount?.let { intermediaryAccount ->
                                CompanyDetailsCard(
                                    modifier = Modifier.fillMaxWidth(),
                                    title = stringResource(Res.string.company_details_pay_account),
                                    onEditClick = {
                                        callbacks.onEditPayAccount(intermediaryAccount.id)
                                    }
                                ) {
                                    CompanyDetailsRow(
                                        title = stringResource(Res.string.company_details_pay_account_swift),
                                        content = intermediaryAccount.swift
                                    )
                                    CompanyDetailsRow(
                                        title = stringResource(Res.string.company_details_pay_account_swift),
                                        content = intermediaryAccount.iban
                                    )
                                    CompanyDetailsRow(
                                        title = stringResource(Res.string.company_details_pay_account_bank_name),
                                        content = intermediaryAccount.bankName
                                    )
                                    CompanyDetailsRow(
                                        title = stringResource(Res.string.company_details_pay_account_bank_address),
                                        content = intermediaryAccount.bankAddress
                                    )
                                }
                            }
                        }

                    CompanyDetailsMode.Error -> ErrorFeedback(
                        modifier = Modifier.fillMaxSize(),
                        onPrimaryAction = callbacks.onRetry
                    )
                }
            }
        }
    }

    data class Callbacks(
        val onBack: () -> Unit,
        val onEditPayAccount: (String) -> Unit,
        val onEditIntermediaryAccount: (String) -> Unit,
        val onEditAddress: () -> Unit,
        val onRetry: () -> Unit,
    )
}