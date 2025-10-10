package io.github.monolithic.invoicer.features.company.presentation.screens.select

import androidx.compose.runtime.Composable
import app.cash.paparazzi.Paparazzi
import io.github.monolithic.invoicer.features.company.services.domain.model.ListCompaniesItemModel
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.snackbar.props.rememberInkSnackBarHostState
import io.github.monolithic.invoicer.foundation.navigation.args.SelectCompanyIntent
import io.github.monolithic.invoicer.foundation.utils.snapshot.MultiplatformSnapshot
import kotlinx.collections.immutable.toPersistentList
import org.junit.Rule
import kotlin.test.Test

class SelectCompanyScreenshotTest {

    @get:Rule
    val paparazzi = Paparazzi(
        maxPercentDifference = 0.01,
    )

    @Test
    fun loadingState() {
        paparazzi.snapshot {
            TestContent(
                state = SelectCompanyState(
                    mode = SelectCompanyMode.Loading
                )
            )
        }
    }

    @Test
    fun emptyState() {
        paparazzi.snapshot {
            TestContent(
                state = SelectCompanyState(
                    mode = SelectCompanyMode.EmptyState
                )
            )
        }
    }

    @Test
    fun errorState() {
        paparazzi.snapshot {
            TestContent(
                state = SelectCompanyState(
                    mode = SelectCompanyMode.Error
                )
            )
        }
    }

    @Test
    fun filledList() {
        paparazzi.snapshot {
            TestContent(
                state = SelectCompanyState(
                    mode = SelectCompanyMode.List,
                    companies = companies
                )
            )
        }
    }

    @Composable
    private fun TestContent(
        state: SelectCompanyState
    ) {
        MultiplatformSnapshot {
            SelectCompanyScreen(
                intent = SelectCompanyIntent.ChangeCompany
            ).StateContent(
                state = state,
                screenActions = SelectCompanyScreen.Actions(
                    onSelectCompany = { },
                    onRetry = { },
                    onCreateNewCompany = { },
                    onBack = { },
                    onConfirm = { }
                ),
                snackBarState = rememberInkSnackBarHostState()
            )
        }
    }

    companion object {
        val companies = (1..5).map {
            ListCompaniesItemModel(
                document = "Document $it",
                name = "Company $it",
                id = it.toString()
            )
        }.toPersistentList()
    }
}
