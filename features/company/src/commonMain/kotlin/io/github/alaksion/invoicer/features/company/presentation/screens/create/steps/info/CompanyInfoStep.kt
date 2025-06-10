package io.github.alaksion.invoicer.features.company.presentation.screens.create.steps.info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import invoicer.features.company.generated.resources.Res
import invoicer.features.company.generated.resources.crate_company_info_title
import invoicer.features.company.generated.resources.create_company_continue
import invoicer.features.company.generated.resources.create_company_info_description
import io.github.alaksion.invoicer.foundation.designSystem.components.ScreenTitle
import io.github.alaksion.invoicer.foundation.designSystem.components.buttons.CloseButton
import io.github.alaksion.invoicer.foundation.designSystem.components.buttons.PrimaryButton
import io.github.alaksion.invoicer.foundation.designSystem.components.spacer.SpacerSize
import io.github.alaksion.invoicer.foundation.designSystem.components.spacer.VerticalSpacer
import io.github.alaksion.invoicer.foundation.designSystem.tokens.Spacing
import org.jetbrains.compose.resources.stringResource


internal class CompanyInfoStep : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current?.parent
        val screenModel = koinScreenModel<CompanyInfoScreenModel>()
        val state = screenModel.state.collectAsState()

        LaunchedEffect(Unit) { screenModel.resumeState() }

        StateContent(
            state = state.value,
            onClose = { navigator?.pop() }
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun StateContent(
        onClose: () -> Unit,
        state: CompanyInfoState
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {},
                    navigationIcon = {
                        CloseButton(onBackClick = onClose)
                    }
                )
            },
            bottomBar = {
                PrimaryButton(
                    label = stringResource(Res.string.create_company_continue),
                    onClick = {},
                    isEnabled = state.isButtonEnabled,
                    modifier = Modifier.fillMaxWidth().padding(Spacing.medium),
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
                    title = stringResource(Res.string.crate_company_info_title),
                    subTitle = stringResource(Res.string.create_company_info_description)
                )
                VerticalSpacer(SpacerSize.XLarge3)
            }
        }
    }
}