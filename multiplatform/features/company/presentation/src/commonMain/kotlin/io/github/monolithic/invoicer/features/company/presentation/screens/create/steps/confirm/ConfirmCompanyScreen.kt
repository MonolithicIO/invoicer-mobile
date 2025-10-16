package io.github.monolithic.invoicer.features.company.presentation.screens.create.steps.confirm

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import invoicer.multiplatform.features.company.presentation.generated.resources.Res
import invoicer.multiplatform.features.company.presentation.generated.resources.create_company_confirmation_cta
import invoicer.multiplatform.features.company.presentation.generated.resources.create_company_confirmation_description
import invoicer.multiplatform.features.company.presentation.generated.resources.create_company_confirmation_document_label
import invoicer.multiplatform.features.company.presentation.generated.resources.create_company_confirmation_title
import invoicer.multiplatform.foundation.design_system.generated.resources.DsResources
import invoicer.multiplatform.foundation.design_system.generated.resources.danger_circle
import io.github.monolithic.invoicer.features.company.presentation.screens.create.steps.confirm.components.AddressSection
import io.github.monolithic.invoicer.features.company.presentation.screens.create.steps.confirm.components.ConfirmCompanyHeader
import io.github.monolithic.invoicer.features.company.presentation.screens.create.steps.confirm.components.PaySection
import io.github.monolithic.invoicer.features.company.presentation.screens.create.steps.success.CreateCompanySuccessScreen
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.SpacerSize
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.VerticalSpacer
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.InkPrimaryButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.divider.InkHorizontalDivider
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.scaffold.InkScaffold
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.snackbar.InkSnackBarHost
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.snackbar.props.InkSnackBarHostState
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.snackbar.props.rememberInkSnackBarHostState
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.topbar.InkTopBar
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.LabeledListItem
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.Title
import io.github.monolithic.invoicer.foundation.utils.compose.FlowCollectEffect
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

internal class ConfirmCompanyScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        val screenModel = koinScreenModel<ConfirmCompanyScreenModel>()
        val state = screenModel.state.collectAsState()
        val scope = rememberCoroutineScope()
        val snackBarState = rememberInkSnackBarHostState()
        val snackBarErrorIcon = painterResource(DsResources.drawable.danger_circle)

        LaunchedEffect(Unit) { screenModel.resumeState() }

        FlowCollectEffect(
            flow = screenModel.events,
            key = screenModel,
        ) {
            when (it) {
                is CreateCompanyEvents.Success -> navigator?.push(CreateCompanySuccessScreen())

                is CreateCompanyEvents.Error -> {
                    scope.launch {
                        snackBarState.showSnackBar(
                            message = it.message,
                            leadingIcon = snackBarErrorIcon
                        )
                    }
                }
            }
        }

        StateContent(
            state = state.value,
            actions = remember {
                Actions(
                    onBack = { navigator?.pop() },
                    onConfirm = screenModel::createCompany,
                    onScrollEnd = screenModel::enableButton
                )
            },
            snackbarHostState = snackBarState
        )
    }

    @Composable
    fun StateContent(
        state: ConfirmCompanyState,
        actions: Actions,
        snackbarHostState: InkSnackBarHostState
    ) {
        InkScaffold(
            snackBarHost = { InkSnackBarHost(snackbarHostState) },
            topBar = {
                InkTopBar(
                    onNavigationClick = actions.onBack,
                    modifier = Modifier.statusBarsPadding()
                )
            },
            bottomBar = {
                InkPrimaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(InkTheme.spacing.medium)
                        .navigationBarsPadding(),
                    text = stringResource(Res.string.create_company_confirmation_cta),
                    onClick = actions.onConfirm,
                    enabled = state.isButtonEnabled,
                    loading = state.isButtonLoading
                )
            },
        ) { scaffoldPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(scaffoldPadding)
                    .padding(InkTheme.spacing.medium)
            ) {
                val scrollState = rememberScrollState()

                LaunchedEffect(scrollState.value) {
                    if (scrollState.value >= scrollState.maxValue) {
                        actions.onScrollEnd()
                    }
                }

                Title(
                    title = stringResource(Res.string.create_company_confirmation_title),
                    subtitle = stringResource(Res.string.create_company_confirmation_description)
                )

                VerticalSpacer(SpacerSize.Medium)

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(scrollState),
                    verticalArrangement = Arrangement.spacedBy(InkTheme.spacing.medium)
                ) {
                    ConfirmCompanyHeader(
                        companyName = state.companyName,
                        modifier = Modifier.fillMaxWidth()
                    )

                    InkHorizontalDivider()

                    LabeledListItem(
                        label = stringResource(Res.string.create_company_confirmation_document_label),
                        value = state.companyDocument,
                        modifier = Modifier.fillMaxWidth()
                    )

                    InkHorizontalDivider()

                    AddressSection(
                        addressLine1 = state.addressLine1,
                        addressLine2 = state.addressLine2,
                        city = state.city,
                        state = state.state,
                        postalCode = state.postalCode,
                        countryCode = state.countryCode
                    )

                    InkHorizontalDivider()

                    PaySection(
                        swift = state.primaryPayAccount.swift,
                        iban = state.primaryPayAccount.iban,
                        bankName = state.primaryPayAccount.bankName,
                        bankAddress = state.primaryPayAccount.bankAddress,
                    )

                    state.intermediaryPayAccount?.let { account ->
                        InkHorizontalDivider()
                        PaySection(
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

    data class Actions(
        val onBack: () -> Unit,
        val onConfirm: () -> Unit,
        val onScrollEnd: () -> Unit,
    )
}
