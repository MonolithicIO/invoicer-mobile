package io.github.alaksion.invoicer.features.invoice.presentation.screens.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import io.github.alaksion.invoicer.foundation.designSystem.components.ScreenTitle
import io.github.alaksion.invoicer.foundation.designSystem.components.buttons.BackButton
import io.github.alaksion.invoicer.foundation.designSystem.components.spacer.SpacerSize
import io.github.alaksion.invoicer.foundation.designSystem.components.spacer.VerticalSpacer
import io.github.alaksion.invoicer.foundation.designSystem.tokens.AppColor
import io.github.alaksion.invoicer.foundation.designSystem.tokens.Spacing
import io.github.alaksion.invoicer.foundation.utils.money.moneyFormat

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
                    title = {},
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
                    .verticalScroll(scrollState)
            ) {
                ScreenTitle(
                    title = "Invoice Details",
                    subTitle = null
                )
                VerticalSpacer(SpacerSize.XLarge3)
                Text(
                    text = state.invoiceTotal.moneyFormat(),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = AppColor.MoneyGreen,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Text(
                    text = "Invoice Number",
                    style = MaterialTheme.typography.headlineMedium,
                )
                Text(
                    text = state.invoiceNumber,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
    }
}
