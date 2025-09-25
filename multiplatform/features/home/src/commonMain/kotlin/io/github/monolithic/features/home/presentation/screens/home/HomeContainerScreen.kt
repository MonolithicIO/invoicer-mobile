package io.github.monolithic.features.home.presentation.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import invoicer.multiplatform.features.home.generated.resources.Res
import invoicer.multiplatform.features.home.generated.resources.ic_plus
import io.github.monolithic.features.home.presentation.screens.home.components.HomeBottomBar
import io.github.monolithic.features.home.presentation.screens.home.components.HomeTopBar
import io.github.monolithic.features.home.presentation.screens.home.tabs.welcome.WelcomeTab
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.icon.InkIconButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.scaffold.InkScaffold
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import io.github.monolithic.invoicer.foundation.navigation.InvoicerScreen
import io.github.monolithic.invoicer.foundation.navigation.extensions.getScreen
import io.github.monolithic.invoicer.foundation.utils.modifier.systemBarBottomPadding
import org.jetbrains.compose.resources.painterResource

internal class HomeContainerScreen : Screen {
    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<HomeScreenModel>()
        val state by screenModel.state.collectAsState()
        val navigator = LocalNavigator.current

        StateContent(
            state = state,
            actions = remember {
                Actions(
                    onNewInvoiceClick = {
                        navigator?.push(getScreen(InvoicerScreen.Invoices.Create))
                    },
                    onCustomersClick = {
                        navigator?.push(getScreen(InvoicerScreen.Customer.List))
                    }
                )
            }
        )
    }

    @Composable
    fun StateContent(
        state: HomeScreenState,
        actions: Actions
    ) {
        TabNavigator(WelcomeTab) { navigator ->
            InkScaffold(
                containerColor = InkTheme.colorScheme.background,
                topBar = {
                    if (navigator.current is WelcomeTab) {
                        HomeTopBar(
                            companyName = state.companyName,
                            modifier = Modifier
                                .fillMaxWidth()
                                .statusBarsPadding(),
                            backgroundColor = InkTheme.colorScheme.background
                        )
                    }
                },
                bottomBar = {
                    HomeBottomBar(
                        modifier = Modifier.systemBarBottomPadding(),
                        selectedTab = navigator.current,
                        onSelectTab = { newTab ->
                            navigator.current = newTab
                        },
                        onCustomersClick = actions.onCustomersClick
                    )
                },
                floatingActionButton = {
                    InkIconButton(
                        icon = painterResource(Res.drawable.ic_plus),
                        onClick = actions.onNewInvoiceClick
                    )
                }
            ) {
                Box(
                    modifier = Modifier.Companion
                        .fillMaxSize()
                        .padding(it)
                ) {
                    CurrentTab()
                }
            }
        }
    }

    data class Actions(
        val onNewInvoiceClick: () -> Unit,
        val onCustomersClick: () -> Unit,
    )
}