package io.github.alaksion.invoicer.features.invoice.presentation.screens.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import invoicer.features.invoice.generated.resources.Res
import invoicer.features.invoice.generated.resources.invoice_details_activity_title
import invoicer.features.invoice.generated.resources.invoice_details_company_name_address_label
import invoicer.features.invoice.generated.resources.invoice_details_company_name_label
import invoicer.features.invoice.generated.resources.invoice_details_company_title
import invoicer.features.invoice.generated.resources.invoice_details_customer_name_label
import invoicer.features.invoice.generated.resources.invoice_details_customer_title
import invoicer.features.invoice.generated.resources.invoice_details_number
import invoicer.features.invoice.generated.resources.invoice_details_pay_bank_address
import invoicer.features.invoice.generated.resources.invoice_details_pay_bank_name
import invoicer.features.invoice.generated.resources.invoice_details_pay_iban
import invoicer.features.invoice.generated.resources.invoice_details_pay_swift
import invoicer.features.invoice.generated.resources.invoice_details_primary_pay_title
import invoicer.features.invoice.generated.resources.invoice_details_title
import io.github.alaksion.invoicer.features.invoice.presentation.screens.details.components.InvoiceDetailsActivityItem
import io.github.alaksion.invoicer.features.invoice.presentation.screens.details.components.InvoiceDetailsTopic
import io.github.alaksion.invoicer.features.invoice.presentation.screens.details.components.InvoiceDetailsTopicItem
import io.github.alaksion.invoicer.foundation.designSystem.components.buttons.BackButton
import io.github.alaksion.invoicer.foundation.designSystem.tokens.AppColor
import io.github.alaksion.invoicer.foundation.designSystem.tokens.Spacing
import io.github.alaksion.invoicer.foundation.utils.money.moneyFormat
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
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(Res.string.invoice_details_title)
                        )
                    },
                    navigationIcon = {
                        BackButton(onBackClick = onBackPress)
                    }
                )
            }
        ) { scaffoldPadding ->
            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(scaffoldPadding)
                    .padding(Spacing.medium)
                    .verticalScroll(scrollState),
                verticalArrangement = Arrangement.spacedBy(Spacing.medium)
            ) {
                Text(
                    text = state.invoiceTotal.moneyFormat(),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = AppColor.MoneyGreen,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Text(
                    text = stringResource(Res.string.invoice_details_number),
                    style = MaterialTheme.typography.headlineMedium,
                )
                Text(
                    text = state.invoiceNumber,
                    style = MaterialTheme.typography.bodyLarge,
                )

                InvoiceDetailsTopic(
                    modifier = Modifier.fillMaxWidth(),
                    title = stringResource(Res.string.invoice_details_company_title)
                ) {
                    InvoiceDetailsTopicItem(
                        title = stringResource(Res.string.invoice_details_company_name_label),
                        content = state.companyName
                    )
                    InvoiceDetailsTopicItem(
                        title = stringResource(Res.string.invoice_details_company_name_address_label),
                        content = state.companyAddress
                    )
                }

                InvoiceDetailsTopic(
                    modifier = Modifier.fillMaxWidth(),
                    title = stringResource(Res.string.invoice_details_customer_title)
                ) {
                    InvoiceDetailsTopicItem(
                        title = stringResource(Res.string.invoice_details_customer_name_label),
                        content = state.customerName
                    )
                }

                InvoiceDetailsTopic(
                    modifier = Modifier.fillMaxWidth(),
                    title = stringResource(Res.string.invoice_details_primary_pay_title)
                ) {
                    InvoiceDetailsTopicItem(
                        title = stringResource(Res.string.invoice_details_pay_swift),
                        content = state.primaryAccount.swift
                    )

                    InvoiceDetailsTopicItem(
                        title = stringResource(Res.string.invoice_details_pay_iban),
                        content = state.primaryAccount.iban
                    )

                    InvoiceDetailsTopicItem(
                        title = stringResource(Res.string.invoice_details_pay_bank_name),
                        content = state.primaryAccount.bankName
                    )

                    InvoiceDetailsTopicItem(
                        title = stringResource(Res.string.invoice_details_pay_bank_address),
                        content = state.primaryAccount.bankAddress
                    )
                }

                state.intermediaryAccount?.let { intermediary ->
                    InvoiceDetailsTopic(
                        modifier = Modifier.fillMaxWidth(),
                        title = stringResource(Res.string.invoice_details_primary_pay_title)
                    ) {
                        InvoiceDetailsTopicItem(
                            title = stringResource(Res.string.invoice_details_pay_swift),
                            content = intermediary.swift
                        )

                        InvoiceDetailsTopicItem(
                            title = stringResource(Res.string.invoice_details_pay_iban),
                            content = intermediary.iban
                        )

                        InvoiceDetailsTopicItem(
                            title = stringResource(Res.string.invoice_details_pay_bank_name),
                            content = intermediary.bankName
                        )

                        InvoiceDetailsTopicItem(
                            title = stringResource(Res.string.invoice_details_pay_bank_address),
                            content = intermediary.bankAddress
                        )
                    }
                }

                InvoiceDetailsTopic(
                    modifier = Modifier.fillMaxWidth(),
                    title = stringResource(Res.string.invoice_details_activity_title)
                ) {
                    state.activities.forEach {
                        InvoiceDetailsActivityItem(
                            name = it.description,
                            quantity = it.quantity.toString(),
                            unitPrice = it.unitPrice.moneyFormat()
                        )
                    }
                }
            }
        }
    }
}
