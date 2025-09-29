package io.github.monolithic.invoicer.features.invoice.screens.invoicelist.screenshots

import androidx.compose.runtime.Composable
import app.cash.paparazzi.Paparazzi
import io.github.monolithic.invoicer.features.invoice.presentation.screens.invoicelist.InvoiceListScreen
import io.github.monolithic.invoicer.features.invoice.presentation.screens.invoicelist.state.InvoiceListMode
import io.github.monolithic.invoicer.features.invoice.presentation.screens.invoicelist.state.InvoiceListState
import io.github.monolithic.invoicer.features.invoice.presentation.screens.invoicelist.state.rememberInvoiceListCallbacks
import io.github.monolithic.invoicer.features.invoice.services.domain.model.InvoiceListItem
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.snackbar.props.InkSnackBarHostState
import io.github.monolithic.invoicer.foundation.designSystem.legacy.theme.InvoicerTheme
import io.github.monolithic.invoicer.foundation.utils.snapshot.MultiplatformSnapshot
import kotlinx.collections.immutable.persistentListOf
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import org.junit.Rule
import org.junit.Test
import kotlin.test.Ignore

class InvoiceListScreenshotTest {

    @get:Rule
    val paparazzi = Paparazzi()

    @Test
    fun invoiceList_empty() {
        paparazzi.snapshot {
            TestContent(
                state = InvoiceListState(
                    mode = InvoiceListMode.Content,
                    invoices = persistentListOf()
                )
            )
        }
    }

    @Test
    @Ignore
    fun invoiceList_filled() {
        paparazzi.snapshot {
            TestContent(
                state = InvoiceListState(
                    mode = InvoiceListMode.Content,
                    invoices = persistentListOf(
                        InvoiceListItem(
                            id = "1",
                            externalId = "ExternalId",
                            issueDate = LocalDate.parse("2023-10-01"),
                            dueDate = LocalDate.parse("2023-10-01"),
                            createdAt = Instant.parse("2023-10-01T00:00:00Z"),
                            updatedAt = Instant.parse("2023-10-01T00:00:00Z"),
                            totalAmount = 100L,
                            customerName = "Customer Name",
                            companyName = "Company Name",
                        ),
                    )
                )
            )
        }
    }

    @Test
    fun invoiceList_loading() {
        paparazzi.snapshot {
            TestContent(
                state = InvoiceListState(
                    mode = InvoiceListMode.Loading,
                    invoices = persistentListOf()
                )
            )
        }
    }

    @Test
    fun invoiceList_error() {
        paparazzi.snapshot {
            TestContent(
                state = InvoiceListState(
                    mode = InvoiceListMode.Error,
                    invoices = persistentListOf()
                )
            )
        }
    }

    @Composable
    private fun TestContent(
        state: InvoiceListState
    ) {
        MultiplatformSnapshot {
            InvoicerTheme {
                InvoiceListScreen()
                    .StateContent(
                        state = state,
                        callbacks = rememberInvoiceListCallbacks(
                            onClose = {},
                            onRetry = {},
                            onClickInvoice = {},
                            onClickCreateInvoice = {},
                            onNextPage = {},
                        ),
                        snackbarHostState = InkSnackBarHostState(),
                    )
            }
        }
    }
}
