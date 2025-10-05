package io.github.monolithic.invoicer.features.invoice.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.github.monolithic.invoicer.features.invoice.presentation.model.InvoicePayAccountUiModel
import io.github.monolithic.invoicer.features.invoice.presentation.screens.details.InvoiceDetailsMode
import io.github.monolithic.invoicer.features.invoice.presentation.screens.details.InvoiceDetailsScreen
import io.github.monolithic.invoicer.features.invoice.presentation.screens.details.InvoiceDetailsState
import io.github.monolithic.invoicer.features.invoice.services.domain.model.InvoiceDetailsActivityModel
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.theme.InvoicerInkTheme
import io.github.monolithic.invoicer.foundation.utils.date.Default
import kotlinx.collections.immutable.toPersistentList
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
@Composable
@Preview
private fun DetailsPreview() {
    InvoicerInkTheme(isDarkTheme = true) {
        InvoiceDetailsScreen("").StateContent(
            state = InvoiceDetailsState(
                invoiceNumber = "123",
                companyName = "Company 1",
                companyAddress = "Address 1",
                customerName = "Customer 1",
                issueDate = LocalDate.Default,
                dueDate = LocalDate.Default,
                primaryAccount = InvoicePayAccountUiModel(
                    swift = "123-SWIFT",
                    iban = "123-IBAN",
                    bankName = "Bank of America",
                    bankAddress = "Bank Av."
                ),
                intermediaryAccount = InvoicePayAccountUiModel(
                    swift = "321-SWIFT",
                    iban = "321-IBAN",
                    bankName = "Bank of Brazil",
                    bankAddress = "Bank St."
                ),
                activities = (1..5).map {
                    InvoiceDetailsActivityModel(
                        id = (it * 10).toString(),
                        description = "Activity $it",
                        quantity = it,
                        unitPrice = it.toLong() * 10,
                    )
                }.toPersistentList(),
                createdAt = Instant.parse("2023-01-01T00:00:00Z"),
                mode = InvoiceDetailsMode.Content,
                id = Uuid.random().toString()
            ),
            onBackPress = {}
        )
    }
}
