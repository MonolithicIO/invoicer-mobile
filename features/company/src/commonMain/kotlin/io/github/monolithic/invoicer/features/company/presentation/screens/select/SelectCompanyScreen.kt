package io.github.monolithic.invoicer.features.company.presentation.screens.select

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import cafe.adriel.voyager.core.annotation.InternalVoyagerApi
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.internal.BackHandler
import invoicer.features.company.generated.resources.Res
import invoicer.features.company.generated.resources.company_error_message
import invoicer.features.company.generated.resources.company_error_retry
import invoicer.features.company.generated.resources.company_error_title
import invoicer.features.company.generated.resources.company_selection_new_company
import invoicer.features.company.generated.resources.company_selection_no_companies_description
import invoicer.features.company.generated.resources.company_selection_no_companies_title
import invoicer.features.company.generated.resources.company_selection_title
import io.github.monolithic.invoicer.features.company.presentation.screens.create.CreateCompanyFlow
import io.github.monolithic.invoicer.foundation.designSystem.legacy.components.LoadingState
import io.github.monolithic.invoicer.foundation.designSystem.legacy.components.ScreenTitle
import io.github.monolithic.invoicer.foundation.designSystem.legacy.components.buttons.CloseButton
import io.github.monolithic.invoicer.foundation.designSystem.legacy.components.buttons.PrimaryButton
import io.github.monolithic.invoicer.foundation.designSystem.legacy.components.card.ListCard
import io.github.monolithic.invoicer.foundation.designSystem.legacy.components.feedback.Feedback
import io.github.monolithic.invoicer.foundation.designSystem.legacy.components.screenstate.EmptyState
import io.github.monolithic.invoicer.foundation.designSystem.legacy.components.spacer.SpacerSize
import io.github.monolithic.invoicer.foundation.designSystem.legacy.components.spacer.VerticalSpacer
import io.github.monolithic.invoicer.foundation.designSystem.legacy.tokens.Spacing
import io.github.monolithic.invoicer.foundation.navigation.InvoicerScreen
import io.github.monolithic.invoicer.foundation.navigation.args.SelectCompanyIntent
import org.jetbrains.compose.resources.stringResource

internal class SelectCompanyScreen(
    private val intent: SelectCompanyIntent
) : Screen {

    @OptIn(InternalVoyagerApi::class)
    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<SelectCompanyScreenModel>()
        val navigator = LocalNavigator.current
        val state = screenModel.state.collectAsState()

        LaunchedEffect(screenModel) {
            screenModel.loadCompanies(
                autoSelectFirst = intent == SelectCompanyIntent.StartupSelection
            )
        }

        LaunchedEffect(screenModel) {
            screenModel.events.collect {
                when (it) {
                    SelectCompanyEvent.ContinueToHome -> navigator?.replaceAll(
                        ScreenRegistry.get(InvoicerScreen.Home)
                    )
                }
            }
        }

        BackHandler(true) { /* no op */ }

        StateContent(
            state = state.value,
            onSelectCompanyClick = {
                screenModel.selectCompany(it)
            },
            onCreateCompanyClick = {
                navigator?.push(CreateCompanyFlow())
            },
            onRetryClick = {
                screenModel.loadCompanies(false)
            },
            onBack = { navigator?.pop() }
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun StateContent(
        state: SelectCompanyState,
        onCreateCompanyClick: () -> Unit,
        onSelectCompanyClick: (companyId: String) -> Unit,
        onRetryClick: () -> Unit,
        onBack: () -> Unit,
    ) {
        Scaffold(
            modifier = Modifier
                .imePadding()
                .systemBarsPadding(),
            topBar = {
                TopAppBar(
                    title = {},
                    navigationIcon = {
                        CloseButton(onBackClick = onBack)
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
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            verticalArrangement = Arrangement.spacedBy(Spacing.medium)
                        ) {
                            items(
                                items = state.companies,
                                key = { it.id }
                            ) {
                                ListCard(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { onSelectCompanyClick(it.id) },
                                    leadingContent = {
                                        Icon(
                                            imageVector = Icons.Outlined.Business,
                                            contentDescription = null
                                        )
                                    },
                                    trailingContent = {
                                        Icon(
                                            imageVector = Icons.Outlined.ChevronRight,
                                            contentDescription = null
                                        )
                                    },
                                    content = {
                                        Column {
                                            Text(
                                                text = it.name,
                                                style = MaterialTheme.typography.bodyLarge,
                                                fontWeight = FontWeight.SemiBold
                                            )
                                            Text(
                                                text = it.document,
                                            )
                                        }
                                    }
                                )
                            }
                        }

                        VerticalSpacer(height = SpacerSize.Medium)
                        PrimaryButton(
                            modifier = Modifier.fillMaxWidth(),
                            label = stringResource(Res.string.company_selection_new_company),
                            onClick = onCreateCompanyClick
                        )
                    }

                    SelectCompanyMode.Error -> {
                        Feedback(
                            modifier = Modifier.fillMaxSize(),
                            primaryActionText = stringResource(Res.string.company_error_retry),
                            onPrimaryAction = onRetryClick,
                            title = stringResource(Res.string.company_error_title),
                            description = stringResource(Res.string.company_error_message),
                            icon = Icons.Outlined.ErrorOutline
                        )
                    }

                    SelectCompanyMode.CreateCompany -> {
                        EmptyState(
                            title = stringResource(Res.string.company_selection_no_companies_title),
                            description = stringResource(Res.string.company_selection_no_companies_description),
                            modifier = Modifier.weight(1f).fillMaxWidth()
                        )
                        VerticalSpacer(height = SpacerSize.Medium)
                        PrimaryButton(
                            modifier = Modifier.fillMaxWidth(),
                            label = stringResource(Res.string.company_selection_new_company),
                            onClick = onCreateCompanyClick
                        )
                    }
                }
            }
        }
    }
}
