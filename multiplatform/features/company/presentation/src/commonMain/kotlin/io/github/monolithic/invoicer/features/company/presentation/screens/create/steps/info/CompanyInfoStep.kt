package io.github.monolithic.invoicer.features.company.presentation.screens.create.steps.info

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusRequester.Companion.FocusRequesterFactory.component1
import androidx.compose.ui.focus.FocusRequester.Companion.FocusRequesterFactory.component2
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import invoicer.multiplatform.features.company.presentation.generated.resources.Res
import invoicer.multiplatform.features.company.presentation.generated.resources.crate_company_info_title
import invoicer.multiplatform.features.company.presentation.generated.resources.create_company_continue
import invoicer.multiplatform.features.company.presentation.generated.resources.create_company_info_description
import invoicer.multiplatform.features.company.presentation.generated.resources.create_company_info_document_hint
import invoicer.multiplatform.features.company.presentation.generated.resources.create_company_info_document_placeholder
import invoicer.multiplatform.features.company.presentation.generated.resources.create_company_info_name_hint
import invoicer.multiplatform.features.company.presentation.generated.resources.create_company_info_name_placeholder
import io.github.monolithic.invoicer.features.company.presentation.screens.create.components.CreateCompanyTopBar
import io.github.monolithic.invoicer.features.company.presentation.screens.create.steps.address.CompanyAddressStep
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.SpacerSize
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.VerticalSpacer
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.InkPrimaryButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.input.InkOutlinedInput
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.scaffold.InkScaffold
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.Title
import org.jetbrains.compose.resources.stringResource


internal class CompanyInfoStep : Screen {

    @Composable
    override fun Content() {
        val parentNavigator = LocalNavigator.current?.parent
        val localNavigator = LocalNavigator.current
        val screenModel = koinScreenModel<CompanyInfoScreenModel>()
        val state = screenModel.state.collectAsState()

        LaunchedEffect(Unit) { screenModel.resumeState() }

        val callbacks = remember {
            Callbacks(
                onClose = { parentNavigator?.pop() },
                onContinue = { localNavigator?.push(CompanyAddressStep()) },
                onNameChange = {
                    screenModel.setName(it)
                },
                onDocumentChange = {
                    screenModel.setDocument(it)
                }
            )
        }

        StateContent(
            state = state.value,
            callbacks = callbacks
        )
    }

    @Composable
    fun StateContent(
        callbacks: Callbacks,
        state: CompanyInfoState
    ) {
        InkScaffold(
            modifier = Modifier.imePadding(),
            topBar = {
                CreateCompanyTopBar(
                    step = 1,
                    onBack = callbacks.onClose,
                    modifier = Modifier.statusBarsPadding()
                )
            },
            bottomBar = {
                InkPrimaryButton(
                    text = stringResource(Res.string.create_company_continue),
                    onClick = callbacks.onContinue,
                    enabled = state.isButtonEnabled,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(InkTheme.spacing.medium)
                        .navigationBarsPadding(),
                )
            }
        ) { scaffoldPadding ->
            val (nameFocus, documentFocus) = FocusRequester.createRefs()
            val keyboard = LocalSoftwareKeyboardController.current
            val scroll = rememberScrollState()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(scaffoldPadding)
                    .padding(InkTheme.spacing.medium)
                    .verticalScroll(scroll),
                verticalArrangement = Arrangement.spacedBy(InkTheme.spacing.medium)
            ) {
                Title(
                    title = stringResource(Res.string.crate_company_info_title),
                    subtitle = stringResource(Res.string.create_company_info_description)
                )

                VerticalSpacer(SpacerSize.Medium)

                InkOutlinedInput(
                    value = state.companyName,
                    onValueChange = callbacks.onNameChange,
                    label = stringResource(Res.string.create_company_info_name_hint),
                    placeholder = stringResource(Res.string.create_company_info_name_placeholder),
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(nameFocus),
                    maxLines = 1,
                    keyboardActions = KeyboardActions(
                        onNext = { documentFocus.requestFocus() }
                    ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    )
                )

                InkOutlinedInput(
                    value = state.companyDocument,
                    onValueChange = callbacks.onDocumentChange,
                    label = stringResource(Res.string.create_company_info_document_hint),
                    placeholder = stringResource(Res.string.create_company_info_document_placeholder),
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(documentFocus),
                    maxLines = 1,
                    keyboardActions = KeyboardActions(
                        onDone = { keyboard?.hide() }
                    ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    )
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
