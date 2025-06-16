package io.github.alaksion.invoicer.features.company.presentation.screens.create.steps.confirm

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import invoicer.features.company.generated.resources.Res
import invoicer.features.company.generated.resources.create_company_confirmation_cta
import invoicer.features.company.generated.resources.create_company_confirmation_description
import invoicer.features.company.generated.resources.create_company_confirmation_intermediary_section_label
import invoicer.features.company.generated.resources.create_company_confirmation_pay_info_section_label
import invoicer.features.company.generated.resources.create_company_confirmation_title
import io.github.alaksion.invoicer.features.company.presentation.screens.create.steps.confirm.components.AddressSection
import io.github.alaksion.invoicer.features.company.presentation.screens.create.steps.confirm.components.CompanySection
import io.github.alaksion.invoicer.features.company.presentation.screens.create.steps.confirm.components.PaySection
import io.github.alaksion.invoicer.foundation.designSystem.components.ScreenTitle
import io.github.alaksion.invoicer.foundation.designSystem.components.buttons.BackButton
import io.github.alaksion.invoicer.foundation.designSystem.components.buttons.PrimaryButton
import io.github.alaksion.invoicer.foundation.designSystem.components.spacer.SpacerSize
import io.github.alaksion.invoicer.foundation.designSystem.components.spacer.VerticalSpacer
import io.github.alaksion.invoicer.foundation.designSystem.tokens.Spacing
import io.github.alaksion.invoicer.foundation.ui.FlowCollectEffect
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource

internal class ConfirmCompanyScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        val parentNavigator = navigator?.parent
        val screenModel = koinScreenModel<ConfirmCompanyScreenModel>()
        val state = screenModel.state.collectAsState()
        val scope = rememberCoroutineScope()
        val snackBarState = remember { SnackbarHostState() }

        LaunchedEffect(Unit) { screenModel.resumeState() }

        FlowCollectEffect(
            flow = screenModel.events,
            key = screenModel,
        ) {
            when (it) {
                is CreateCompanyEvents.Success -> parentNavigator?.pop()

                is CreateCompanyEvents.Error -> {
                    scope.launch { snackBarState.showSnackbar(it.message) }
                }
            }
        }

        StateContent(
            state = state.value,
            callbacks = remember {
                Callbacks(
                    onBack = { navigator?.pop() },
                    onConfirm = screenModel::createCompany,
                    onScrollEnd = screenModel::enableButton
                )
            }
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun StateContent(
        state: ConfirmCompanyState,
        callbacks: Callbacks
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {},
                    navigationIcon = {
                        BackButton(onBackClick = callbacks.onBack)
                    }
                )
            },
            bottomBar = {
                PrimaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Spacing.medium),
                    label = stringResource(Res.string.create_company_confirmation_cta),
                    onClick = callbacks.onConfirm,
                    isEnabled = state.isButtonEnabled,
                    isLoading = state.isButtonLoading
                )
            }
        ) { scaffoldPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(scaffoldPadding)
                    .padding(Spacing.medium)
            ) {
                val scrollState = rememberScrollState()

                LaunchedEffect(scrollState.value) {
                    if (scrollState.value >= scrollState.maxValue) {
                        callbacks.onScrollEnd()
                    }
                }

                ScreenTitle(
                    title = stringResource(Res.string.create_company_confirmation_title),
                    subTitle = stringResource(Res.string.create_company_confirmation_description)
                )
                VerticalSpacer(SpacerSize.XLarge3)
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(scrollState),
                    verticalArrangement = Arrangement.spacedBy(Spacing.medium)
                ) {

                    CompanySection(
                        name = state.companyName,
                        document = state.companyDocument
                    )

                    AddressSection(
                        addressLine1 = state.addressLine1,
                        addressLine2 = state.addressLine2,
                        city = state.city,
                        state = state.state,
                        postalCode = state.postalCode,
                        countryCode = state.countryCode
                    )

                    PaySection(
                        title = stringResource(Res.string.create_company_confirmation_pay_info_section_label),
                        swift = state.primaryPayAccount.swift,
                        iban = state.primaryPayAccount.iban,
                        bankName = state.primaryPayAccount.bankName,
                        bankAddress = state.primaryPayAccount.bankAddress,
                    )

                    state.intermediaryPayAccount?.let { account ->
                        PaySection(
                            title = stringResource(Res.string.create_company_confirmation_intermediary_section_label),
                            swift = account.swift,
                            iban = account.iban,
                            bankName = account.bankName,
                            bankAddress = account.bankAddress,
                        )
                    }
                }
            }
        }
    }

    data class Callbacks(
        val onBack: () -> Unit,
        val onConfirm: () -> Unit,
        val onScrollEnd: () -> Unit,
    )
}