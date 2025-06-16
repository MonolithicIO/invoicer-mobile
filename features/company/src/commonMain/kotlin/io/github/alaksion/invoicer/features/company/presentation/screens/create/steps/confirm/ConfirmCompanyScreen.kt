package io.github.alaksion.invoicer.features.company.presentation.screens.create.steps.confirm

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import invoicer.features.company.generated.resources.Res
import invoicer.features.company.generated.resources.create_company_confirmation_cta
import invoicer.features.company.generated.resources.create_company_confirmation_description
import invoicer.features.company.generated.resources.create_company_confirmation_title
import io.github.alaksion.invoicer.foundation.designSystem.components.ScreenTitle
import io.github.alaksion.invoicer.foundation.designSystem.components.buttons.BackButton
import io.github.alaksion.invoicer.foundation.designSystem.components.buttons.PrimaryButton
import io.github.alaksion.invoicer.foundation.designSystem.components.spacer.SpacerSize
import io.github.alaksion.invoicer.foundation.designSystem.components.spacer.VerticalSpacer
import io.github.alaksion.invoicer.foundation.designSystem.tokens.Spacing
import org.jetbrains.compose.resources.stringResource

internal class ConfirmCompanyScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        val screenModel = koinScreenModel<ConfirmCompanyScreenModel>()
        val state = screenModel.state.collectAsState()

        StateContent(
            state = state.value,
            callbacks = remember {
                Callbacks(
                    onBack = { navigator?.pop() },
                    onConfirm = {}
                )
            }
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun StateContent(
        state: ConfirmCompanyState,
        callbacks: Callbacks
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {},
                    navigationIcon = {
                        BackButton(onBackClick = callbacks.onBack)
                    }
                )
            },
            bottomBar = {
                PrimaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Spacing.medium),
                    label = stringResource(Res.string.create_company_confirmation_cta),
                    onClick = callbacks.onConfirm
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
                    title = stringResource(Res.string.create_company_confirmation_title),
                    subTitle = stringResource(Res.string.create_company_confirmation_description)
                )
                VerticalSpacer(SpacerSize.XLarge3)
            }
        }
    }

    data class Callbacks(
        val onBack: () -> Unit,
        val onConfirm: () -> Unit,
    )

}