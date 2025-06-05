package io.github.alaksion.invoicer.features.company.presentation.selectcompany

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import invoicer.features.company.generated.resources.Res
import invoicer.features.company.generated.resources.company_selection_title
import io.github.alaksion.invoicer.foundation.designSystem.components.LoadingState
import io.github.alaksion.invoicer.foundation.designSystem.components.ScreenTitle
import io.github.alaksion.invoicer.foundation.designSystem.components.buttons.CloseButton
import io.github.alaksion.invoicer.foundation.designSystem.components.spacer.SpacerSize
import io.github.alaksion.invoicer.foundation.designSystem.components.spacer.VerticalSpacer
import io.github.alaksion.invoicer.foundation.designSystem.tokens.Spacing
import org.jetbrains.compose.resources.stringResource

internal class SelectCompanyScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<SelectCompanyScreenModel>()
        val state = screenModel.state.collectAsState()

        StateContent(
            state = state.value
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun StateContent(
        state: SelectCompanyState,
    ) {
        Scaffold(
            modifier = Modifier.imePadding(),
            topBar = {
                TopAppBar(
                    title = {},
                    navigationIcon = {
                        CloseButton(onBackClick = {})
                    }
                )
            },
        ) { scaffoldPadding ->
            Column(
                modifier = Modifier
                    .padding(scaffoldPadding)
                    .padding(Spacing.medium)
            ) {
                ScreenTitle(
                    title = stringResource(Res.string.company_selection_title),
                    subTitle = null
                )
                VerticalSpacer(height = SpacerSize.XLarge)

                when (state.mode) {
                    SelectCompanyMode.Loading -> LoadingState(
                        modifier = Modifier.fillMaxSize()
                    )

                    SelectCompanyMode.List -> {
                        // Show list of companies
                    }

                    SelectCompanyMode.Error -> {
                        // Show error message
                    }

                    SelectCompanyMode.CreateCompany -> {
                        // Show create company form
                    }
                }
            }
        }
    }
}