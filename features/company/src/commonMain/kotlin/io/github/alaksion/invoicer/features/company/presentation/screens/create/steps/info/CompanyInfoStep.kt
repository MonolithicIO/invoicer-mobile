package io.github.alaksion.invoicer.features.company.presentation.screens.create.steps.info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import invoicer.features.company.generated.resources.Res
import invoicer.features.company.generated.resources.crate_company_info_title
import invoicer.features.company.generated.resources.create_company_continue
import invoicer.features.company.generated.resources.create_company_info_description
import invoicer.features.company.generated.resources.create_company_info_document_hint
import invoicer.features.company.generated.resources.create_company_info_document_placeholder
import invoicer.features.company.generated.resources.create_company_info_name_hint
import invoicer.features.company.generated.resources.create_company_info_name_placeholder
import io.github.alaksion.invoicer.foundation.designSystem.components.InputField
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
        val parentNavigator = LocalNavigator.current?.parent
        val screenModel = koinScreenModel<CompanyInfoScreenModel>()
        val state = screenModel.state.collectAsState()

        LaunchedEffect(Unit) { screenModel.resumeState() }

        StateContent(
            state = state.value,
            callbacks = remember {
                Callbacks(
                    onClose = { parentNavigator?.pop() },
                    onContinue = { },
                    onNameChange = screenModel::setName,
                    onDocumentChange = screenModel::setDocument
                )
            }
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun StateContent(
        callbacks: Callbacks,
        state: CompanyInfoState
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {},
                    navigationIcon = {
                        CloseButton(onBackClick = callbacks.onClose)
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
                InputField(
                    value = state.companyName,
                    onValueChange = callbacks.onNameChange,
                    label = { Text(text = stringResource(Res.string.create_company_info_name_hint)) },
                    placeholder = {
                        Text(text = stringResource(Res.string.create_company_info_name_placeholder))
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                InputField(
                    value = state.companyDocument,
                    onValueChange = callbacks.onDocumentChange,
                    label = { Text(text = stringResource(Res.string.create_company_info_document_hint)) },
                    placeholder = {
                        Text(text = stringResource(Res.string.create_company_info_document_placeholder))
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }

    data class Callbacks(
        val onClose: () -> Unit,
        val onContinue: () -> Unit,
        val onNameChange: (String) -> Unit,
        val onDocumentChange: (String) -> Unit
    )
}
