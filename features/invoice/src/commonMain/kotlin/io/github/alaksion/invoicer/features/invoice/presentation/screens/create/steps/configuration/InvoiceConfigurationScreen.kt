package io.github.alaksion.invoicer.features.invoice.presentation.screens.create.steps.configuration

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
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
import invoicer.features.invoice.generated.resources.Res
import invoicer.features.invoice.generated.resources.invoice_configuration_description
import invoicer.features.invoice.generated.resources.invoice_configuration_due_date_label
import invoicer.features.invoice.generated.resources.invoice_configuration_issue_date_label
import invoicer.features.invoice.generated.resources.invoice_configuration_title
import invoicer.features.invoice.generated.resources.invoice_create_continue_cta
import io.github.alaksion.invoicer.features.invoice.presentation.screens.create.steps.configuration.components.InvoiceDateField
import io.github.alaksion.invoicer.foundation.designSystem.components.InputField
import io.github.alaksion.invoicer.foundation.designSystem.components.ScreenTitle
import io.github.alaksion.invoicer.foundation.designSystem.components.buttons.BackButton
import io.github.alaksion.invoicer.foundation.designSystem.components.buttons.PrimaryButton
import io.github.alaksion.invoicer.foundation.designSystem.components.spacer.SpacerSize
import io.github.alaksion.invoicer.foundation.designSystem.components.spacer.VerticalSpacer
import io.github.alaksion.invoicer.foundation.designSystem.tokens.Spacing
import kotlinx.datetime.LocalDate
import org.jetbrains.compose.resources.stringResource

internal class InvoiceConfigurationScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<InvoiceConfigurationScreenModel>()
        val state by screenModel.state.collectAsState()
        val navigator = LocalNavigator.current

        val callBacks = remember {
            Callbacks(
                onBack = { navigator?.pop() },
                onNext = {
                    screenModel.saveConfiguration()
                },
                onInvoiceNumberChange = screenModel::updateInvoiceNumber,
                onInvoiceDueDateChange = screenModel::updateInvoiceDueDate,
                onInvoiceIssueDateChange = screenModel::updateIssueDate
            )
        }

        LaunchedEffect(screenModel) { screenModel.refreshState() }

        StateContent(
            state = state,
            callbacks = callBacks
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun StateContent(
        state: InvoiceConfigurationState,
        callbacks: Callbacks
    ) {
        Scaffold(
            modifier = Modifier.imePadding(),
            topBar = {
                TopAppBar(
                    title = {},
                    navigationIcon = {
                        BackButton(
                            onBackClick = callbacks.onBack
                        )
                    }
                )
            },
            bottomBar = {
                PrimaryButton(
                    label = stringResource(Res.string.invoice_create_continue_cta),
                    onClick = callbacks.onNext,
                    modifier = Modifier.fillMaxWidth().padding(Spacing.medium)
                )
            }
        ) { scaffoldPadding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(scaffoldPadding)
                    .padding(Spacing.medium)
            ) {
                ScreenTitle(
                    title = stringResource(Res.string.invoice_configuration_title),
                    subTitle = stringResource(Res.string.invoice_configuration_description)
                )
                VerticalSpacer(SpacerSize.XLarge3)

                InputField(
                    value = state.invoiceNumber,
                    onValueChange = callbacks.onInvoiceNumberChange,
                    modifier = Modifier.fillMaxWidth()
                )

                VerticalSpacer(SpacerSize.Medium)

                InvoiceDateField(
                    label = stringResource(Res.string.invoice_configuration_issue_date_label),
                    content = "",
                    onChangeClick = {}
                )

                InvoiceDateField(
                    label = stringResource(Res.string.invoice_configuration_due_date_label),
                    content = "",
                    onChangeClick = {}
                )
            }
        }
    }

    data class Callbacks(
        val onBack: () -> Unit,
        val onNext: () -> Unit,
        val onInvoiceNumberChange: (String) -> Unit,
        val onInvoiceDueDateChange: (LocalDate) -> Unit,
        val onInvoiceIssueDateChange: (LocalDate) -> Unit,
    )
}