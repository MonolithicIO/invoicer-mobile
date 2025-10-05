package io.github.monolithic.invoicer.features.invoice.presentation.screens.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.util.fastForEach
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import invoicer.multiplatform.features.invoice.presentation.generated.resources.Res
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_details_activity_title
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_details_amount
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_details_created_at
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_details_due_date
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_details_intermediary_pay_title
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_details_issue_date
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_details_number
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_details_pay_bank_address
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_details_pay_bank_name
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_details_pay_iban
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_details_pay_swift
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_details_primary_pay_title
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_details_title
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_details_transaction_id
import io.github.monolithic.invoicer.features.invoice.presentation.screens.details.components.InvoiceDetailsActivityCard
import io.github.monolithic.invoicer.features.invoice.presentation.screens.details.components.InvoiceDetailsRow
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkText
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkTextStyle
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.divider.InkHorizontalDivider
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.scaffold.InkScaffold
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.topbar.InkTopBar
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.CompanyNameIcon
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.ErrorState
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.LoadingState
import io.github.monolithic.invoicer.foundation.utils.date.defaultFormat
import io.github.monolithic.invoicer.foundation.utils.date.toDateTimeString
import io.github.monolithic.invoicer.foundation.utils.money.moneyFormat
import org.jetbrains.compose.resources.stringResource

internal data class InvoiceDetailsScreen(
    private val id: String
) : Screen {

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<InvoiceDetailsScreenModel>()
        val navigator = LocalNavigator.current
        val state by screenModel.state.collectAsState()

        LaunchedEffect(Unit) { screenModel.initState(invoiceId = id) }

        StateContent(
            state = state,
            onBackPress = { navigator?.pop() }
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun StateContent(
        state: InvoiceDetailsState,
        onBackPress: () -> Unit,
    ) {

        val verticalScroll = rememberScrollState()
        InkScaffold(
            topBar = {
                InkTopBar(
                    onNavigationClick = onBackPress,
                    title = stringResource(Res.string.invoice_details_title),
                    modifier = Modifier.statusBarsPadding()
                )

            }
        ) { scaffoldPadding ->
            val contentPadding = Modifier.padding(scaffoldPadding).padding(InkTheme.spacing.medium)

            when (state.mode) {
                InvoiceDetailsMode.Content -> DetailsContent(
                    state = state,
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(verticalScroll)
                        .then(contentPadding)
                        .navigationBarsPadding()
                )

                is InvoiceDetailsMode.Error -> ErrorState(
                    modifier = Modifier.fillMaxSize().then(contentPadding)
                )

                InvoiceDetailsMode.Loading -> LoadingState(
                    modifier = Modifier.fillMaxSize().then(contentPadding)
                )
            }
        }
    }

    @Composable
    fun DetailsContent(
        state: InvoiceDetailsState,
        modifier: Modifier = Modifier
    ) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(InkTheme.spacing.medium)
        ) {
            CompanyNameIcon(name = state.customerName)
            InkText(
                text = state.customerName,
                style = InkTextStyle.Heading5,
                weight = FontWeight.SemiBold
            )

            InkHorizontalDivider()

            InvoiceDetailsRow(
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(Res.string.invoice_details_amount),
                value = state.invoiceTotal.moneyFormat(),
                valueStyle = InkTextStyle.Heading5,
                valueColor = InkTheme.colorScheme.primaryVariant,
                valueWeight = FontWeight.Bold
            )

            InkHorizontalDivider()

            InvoiceDetailsRow(
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(Res.string.invoice_details_created_at),
                value = state.createdAt.toDateTimeString(),
            )
            InvoiceDetailsRow(
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(Res.string.invoice_details_issue_date),
                value = state.issueDate.defaultFormat(),
            )
            InvoiceDetailsRow(
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(Res.string.invoice_details_due_date),
                value = state.dueDate.defaultFormat(),
            )

            InkHorizontalDivider()

            InvoiceDetailsRow(
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(Res.string.invoice_details_due_date),
                value = state.dueDate.defaultFormat(),
            )
            InvoiceDetailsRow(
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(Res.string.invoice_details_number),
                value = state.invoiceNumber,
            )
            InvoiceDetailsRow(
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(Res.string.invoice_details_transaction_id),
                value = state.id,
            )

            InkHorizontalDivider()

            InkText(
                text = stringResource(Res.string.invoice_details_primary_pay_title),
                style = InkTextStyle.BodyMedium,
                modifier = Modifier.align(Alignment.Start)
            )

            InvoiceDetailsRow(
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(Res.string.invoice_details_pay_swift),
                value = state.primaryAccount.swift,
            )

            InvoiceDetailsRow(
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(Res.string.invoice_details_pay_iban),
                value = state.primaryAccount.iban,
            )

            InvoiceDetailsRow(
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(Res.string.invoice_details_pay_bank_name),
                value = state.primaryAccount.bankName,
            )

            InvoiceDetailsRow(
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(Res.string.invoice_details_pay_bank_address),
                value = state.primaryAccount.bankAddress,
            )

            InkHorizontalDivider()

            state.intermediaryAccount?.let { intermediaryAccount ->
                InkText(
                    text = stringResource(Res.string.invoice_details_intermediary_pay_title),
                    style = InkTextStyle.BodyMedium,
                    modifier = Modifier.align(Alignment.Start)
                )

                InvoiceDetailsRow(
                    modifier = Modifier.fillMaxWidth(),
                    label = stringResource(Res.string.invoice_details_pay_swift),
                    value = intermediaryAccount.swift,
                )

                InvoiceDetailsRow(
                    modifier = Modifier.fillMaxWidth(),
                    label = stringResource(Res.string.invoice_details_pay_iban),
                    value = intermediaryAccount.iban,
                )

                InvoiceDetailsRow(
                    modifier = Modifier.fillMaxWidth(),
                    label = stringResource(Res.string.invoice_details_pay_bank_name),
                    value = intermediaryAccount.bankName,
                )

                InvoiceDetailsRow(
                    modifier = Modifier.fillMaxWidth(),
                    label = stringResource(Res.string.invoice_details_pay_bank_address),
                    value = intermediaryAccount.bankAddress,
                )

                InkHorizontalDivider()
            }

            InkText(
                text = stringResource(Res.string.invoice_details_activity_title),
                style = InkTextStyle.BodyMedium,
                modifier = Modifier.align(Alignment.Start)
            )
            state.activities.fastForEach {
                InvoiceDetailsActivityCard(
                    description = it.description,
                    quantity = it.quantity.toString(),
                    unitPrice = it.unitPrice.moneyFormat(),
                    totalAmount = it.total.moneyFormat()
                )
            }

        }
    }
}
