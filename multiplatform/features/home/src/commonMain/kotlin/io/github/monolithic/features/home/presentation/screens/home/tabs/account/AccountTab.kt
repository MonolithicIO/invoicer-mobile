package io.github.monolithic.features.home.presentation.screens.home.tabs.account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import invoicer.multiplatform.features.home.generated.resources.Res
import invoicer.multiplatform.features.home.generated.resources.account_change_company
import invoicer.multiplatform.features.home.generated.resources.account_qrcode_auth
import invoicer.multiplatform.features.home.generated.resources.account_settings
import invoicer.multiplatform.features.home.generated.resources.account_sign_out
import invoicer.multiplatform.foundation.design_system.generated.resources.DsResources
import invoicer.multiplatform.foundation.design_system.generated.resources.ic_edit
import invoicer.multiplatform.foundation.design_system.generated.resources.ic_qr_code
import invoicer.multiplatform.foundation.design_system.generated.resources.ic_settings
import invoicer.multiplatform.foundation.design_system.generated.resources.ic_sign_out
import io.github.monolithic.features.home.presentation.screens.home.tabs.account.components.SelectedCompanyCard
import io.github.monolithic.features.home.presentation.screens.home.tabs.account.components.SignOutDialog
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkCard
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.SpacerSize
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.VerticalSpacer
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.ListItem
import io.github.monolithic.invoicer.foundation.designSystem.legacy.tokens.Spacing
import io.github.monolithic.invoicer.foundation.navigation.InvoicerScreen
import io.github.monolithic.invoicer.foundation.navigation.args.SelectCompanyIntent
import io.github.monolithic.invoicer.foundation.navigation.extensions.getScreen
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

internal object AccountTab : Tab {
    override val options: TabOptions
        @Composable
        get() = TabOptions(
            index = 2u,
            title = "",
            icon = null
        )

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current?.parent
        val viewModel = koinScreenModel<AccountTabScreenModel>()
        val state by viewModel.state.collectAsState()
        var signOutDialogState by remember { mutableStateOf(false) }

        val callBacks = remember {
            Actions(
                onChangeCompanyClick = {
                    navigator?.push(
                        getScreen(
                            InvoicerScreen.Company.SelectCompany(
                                intent = SelectCompanyIntent.ChangeCompany
                            )
                        )
                    )
                },
                onSettingsClick = {},
                onQrCodeAuthClick = {
                    navigator?.push(
                        getScreen(
                            InvoicerScreen.Authorization.Home
                        )
                    )
                },
                onDismissSignOut = { signOutDialogState = false },
                onConfirmSignOut = {
                    signOutDialogState = false
                    viewModel.signOut()
                },
                onRequestSignOut = { signOutDialogState = true },
                onCompanyDetailsClick = {
                    navigator?.push(getScreen(InvoicerScreen.Company.Details))
                }
            )
        }

        StateContent(
            actions = callBacks,
            isDialogVisible = signOutDialogState,
            state = state
        )
    }

    @Composable
    fun StateContent(
        state: AccountTabState,
        actions: Actions,
        isDialogVisible: Boolean,
    ) {
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(InkTheme.colorScheme.surfaceLight)
                .padding(Spacing.medium)
                .verticalScroll(scrollState)
        ) {
            SelectedCompanyCard(
                companyName = state.currentCompanyName,
                containerColor = InkTheme.colorScheme.background,
                contentPadding = PaddingValues(InkTheme.spacing.medium),
                onClick = actions.onCompanyDetailsClick
            )

            VerticalSpacer(SpacerSize.Medium)

            InkCard(
                containerColor = InkTheme.colorScheme.background,
                contentPadding = PaddingValues(InkTheme.spacing.medium)
            ) {
                ListItem(
                    text = stringResource(Res.string.account_change_company),
                    icon = painterResource(DsResources.drawable.ic_edit),
                    onClick = actions.onChangeCompanyClick
                )
                ListItem(
                    text = stringResource(Res.string.account_qrcode_auth),
                    icon = painterResource(DsResources.drawable.ic_qr_code),
                    onClick = actions.onQrCodeAuthClick
                )
                ListItem(
                    text = stringResource(Res.string.account_settings),
                    icon = painterResource(DsResources.drawable.ic_settings),
                    onClick = actions.onSettingsClick
                )
                ListItem(
                    text = stringResource(Res.string.account_sign_out),
                    icon = painterResource(DsResources.drawable.ic_sign_out),
                    onClick = actions.onRequestSignOut,
                    contentColor = InkTheme.colorScheme.error,
                    showNavIcon = false,
                )
            }
        }

        SignOutDialog(
            onDismiss = actions.onDismissSignOut,
            onConfirm = actions.onConfirmSignOut,
            isVisible = isDialogVisible
        )
    }

    internal data class Actions(
        val onChangeCompanyClick: () -> Unit,
        val onSettingsClick: () -> Unit,
        val onQrCodeAuthClick: () -> Unit,
        val onDismissSignOut: () -> Unit,
        val onConfirmSignOut: () -> Unit,
        val onRequestSignOut: () -> Unit,
        val onCompanyDetailsClick: () -> Unit,
    )
}
