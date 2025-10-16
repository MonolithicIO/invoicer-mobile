package io.github.monolithic.invoicer.features.company.presentation.screens.select

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import cafe.adriel.voyager.core.annotation.InternalVoyagerApi
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.internal.BackHandler
import invoicer.multiplatform.features.company.presentation.generated.resources.Res
import invoicer.multiplatform.features.company.presentation.generated.resources.company_error_message
import invoicer.multiplatform.features.company.presentation.generated.resources.company_error_retry
import invoicer.multiplatform.features.company.presentation.generated.resources.company_error_title
import invoicer.multiplatform.features.company.presentation.generated.resources.company_selection_invalid_state
import invoicer.multiplatform.features.company.presentation.generated.resources.company_selection_no_companies_description
import invoicer.multiplatform.features.company.presentation.generated.resources.company_selection_no_companies_title
import invoicer.multiplatform.features.company.presentation.generated.resources.company_selection_title
import invoicer.multiplatform.foundation.design_system.generated.resources.DsResources
import invoicer.multiplatform.foundation.design_system.generated.resources.ic_danger_square
import io.github.monolithic.invoicer.features.company.presentation.screens.create.CreateCompanyFlow
import io.github.monolithic.invoicer.features.company.presentation.screens.select.components.SelectCompanyBottomBar
import io.github.monolithic.invoicer.features.company.presentation.screens.select.components.SelectCompanyBottomBarAction
import io.github.monolithic.invoicer.features.company.presentation.screens.select.components.SelectCompanyCard
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.scaffold.InkScaffold
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.snackbar.InkSnackBarHost
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.snackbar.props.InkSnackBarHostState
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.snackbar.props.rememberInkSnackBarHostState
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.topbar.InkTopBar
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.EmptyState
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.ErrorState
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.ErrorStateAction
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.LoadingState
import io.github.monolithic.invoicer.foundation.navigation.InvoicerScreen
import io.github.monolithic.invoicer.foundation.navigation.args.SelectCompanyIntent
import io.github.monolithic.invoicer.foundation.utils.compose.FlowCollectEffect
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
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
        val snackBarState = rememberInkSnackBarHostState()
        val scope = rememberCoroutineScope()
        val errorResources = ErrorResources.rememberErrorResources()


        LaunchedEffect(screenModel) {
            screenModel.loadCompanies(
                autoSelectFirst = intent == SelectCompanyIntent.StartupSelection
            )
        }

        FlowCollectEffect(
            flow = screenModel.events,
            screenModel
        ) {
            when (it) {
                SelectCompanyEvent.ContinueToHome -> navigator?.replaceAll(
                    ScreenRegistry.get(InvoicerScreen.Home)
                )

                SelectCompanyEvent.NoCompanySelected -> scope.launch {
                    snackBarState.showSnackBar(
                        message = errorResources.title,
                        leadingIcon = errorResources.icon
                    )
                }
            }
        }

        BackHandler(true) { /* no op */ }

        StateContent(
            state = state.value,
            screenActions = Actions(
                onSelectCompany = { screenModel.selectCompany(it) },
                onRetry = { screenModel.loadCompanies(false) },
                onCreateNewCompany = { navigator?.push(CreateCompanyFlow()) },
                onBack = { navigator?.pop() },
                onConfirm = { screenModel.submit() }
            ),
            snackBarState = snackBarState
        )
    }

    @Composable
    fun StateContent(
        state: SelectCompanyState,
        screenActions: Actions,
        snackBarState: InkSnackBarHostState
    ) {
        InkScaffold(
            modifier = Modifier,
            topBar = {
                InkTopBar(
                    title = stringResource(Res.string.company_selection_title),
                    onNavigationClick = screenActions.onBack,
                    modifier = Modifier.statusBarsPadding()
                )
            },
            bottomBar = {
                SelectCompanyBottomBar(
                    state = state,
                    modifier = Modifier
                        .padding(InkTheme.spacing.medium)
                        .navigationBarsPadding(),
                    onAction = { action ->
                        when (action) {
                            SelectCompanyBottomBarAction.Continue ->
                                screenActions.onConfirm()

                            SelectCompanyBottomBarAction.CreateNew ->
                                screenActions.onCreateNewCompany()
                        }
                    }
                )
            },
            snackBarHost = { InkSnackBarHost(state = snackBarState) }
        ) { scaffoldPadding ->
            Column(
                modifier = Modifier
                    .padding(scaffoldPadding)
                    .padding(InkTheme.spacing.medium)
            ) {
                when (state.mode) {
                    SelectCompanyMode.Loading -> LoadingState(
                        modifier = Modifier.fillMaxSize()
                    )

                    SelectCompanyMode.List -> {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            verticalArrangement = Arrangement.spacedBy(InkTheme.spacing.medium)
                        ) {
                            items(
                                items = state.companies,
                                key = { it.id }
                            ) {
                                SelectCompanyCard(
                                    isSelected = it.id == state.selectedCompanyId,
                                    companyName = it.name,
                                    companyDocument = it.document,
                                    onSelect = { screenActions.onSelectCompany(it.id) },
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }
                    }

                    SelectCompanyMode.Error -> {
                        ErrorState(
                            modifier = Modifier.fillMaxSize(),
                            title = stringResource(Res.string.company_error_title),
                            description = stringResource(Res.string.company_error_message),
                            primaryAction = ErrorStateAction(
                                label = stringResource(Res.string.company_error_retry),
                                action = screenActions.onRetry
                            )
                        )
                    }

                    SelectCompanyMode.EmptyState -> {
                        EmptyState(
                            title = stringResource(Res.string.company_selection_no_companies_title),
                            description = stringResource(Res.string.company_selection_no_companies_description),
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
        }
    }

    data class Actions(
        val onSelectCompany: (companyId: String) -> Unit,
        val onRetry: () -> Unit,
        val onCreateNewCompany: () -> Unit,
        val onBack: () -> Unit,
        val onConfirm: () -> Unit,
    )

    data class ErrorResources(
        val title: String,
        val icon: Painter
    ) {
        companion object {
            @Composable
            fun rememberErrorResources(): ErrorResources {
                val invalidStateErrorMessage = stringResource(
                    Res.string.company_selection_invalid_state
                )

                val invalidStateIcon = painterResource(
                    DsResources.drawable.ic_danger_square
                )

                return remember {
                    ErrorResources(
                        title = invalidStateErrorMessage,
                        icon = invalidStateIcon
                    )
                }
            }
        }
    }
}
