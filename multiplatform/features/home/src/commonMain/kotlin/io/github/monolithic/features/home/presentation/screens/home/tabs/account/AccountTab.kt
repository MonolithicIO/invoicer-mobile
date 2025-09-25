package io.github.monolithic.features.home.presentation.screens.home.tabs.account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.QrCodeScanner
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import invoicer.multiplatform.features.home.generated.resources.Res
import invoicer.multiplatform.features.home.generated.resources.home_settings_authorization
import invoicer.multiplatform.features.home.generated.resources.home_settings_company
import invoicer.multiplatform.features.home.generated.resources.home_settings_label
import invoicer.multiplatform.features.home.generated.resources.home_settings_profile
import invoicer.multiplatform.features.home.generated.resources.home_settings_sign_out
import io.github.monolithic.features.home.presentation.screens.home.tabs.account.components.SettingsItem
import io.github.monolithic.features.home.presentation.screens.home.tabs.account.components.SignOutDialog
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.scaffold.InkScaffold
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import io.github.monolithic.invoicer.foundation.designSystem.legacy.tokens.Spacing
import io.github.monolithic.invoicer.foundation.navigation.InvoicerScreen
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
        var signOutDialogState by remember { mutableStateOf(false) }

        val callBacks = remember {
            Callbacks(
                onSignOutClick = { signOutDialogState = true },
                onConfirmSignOut = {
                    signOutDialogState = false
                    viewModel.signOut()
                },
                onCancelSignOut = { signOutDialogState = false },
                onAuthorizationClick = {
                    navigator?.push(ScreenRegistry.get(InvoicerScreen.Authorization.Home))
                },
                onProfileClick = {},
                onCompanyClick = {
                    navigator?.push(ScreenRegistry.get(InvoicerScreen.Company.Details))
                },
            )
        }

        StateContent(
            callbacks = callBacks,
            isDialogVisible = signOutDialogState
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun StateContent(
        callbacks: Callbacks,
        isDialogVisible: Boolean,
    ) {
        val scroll = rememberScrollState()
        InkScaffold(
            containerColor = InkTheme.colorScheme.surfaceLight,
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = stringResource(Res.string.home_settings_label))
                    },
                )
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(Spacing.medium)
                    .verticalScroll(scroll),
                verticalArrangement = Arrangement.spacedBy(Spacing.small)
            ) {
                SettingsItem(
                    modifier = Modifier.fillMaxWidth(),
                    content = stringResource(Res.string.home_settings_authorization),
                    icon = Icons.Outlined.QrCodeScanner,
                    onClick = callbacks.onAuthorizationClick
                )
                HorizontalDivider()
                SettingsItem(
                    modifier = Modifier.fillMaxWidth(),
                    content = stringResource(Res.string.home_settings_profile),
                    icon = Icons.Outlined.Person,
                    onClick = callbacks.onProfileClick
                )
                HorizontalDivider()
                SettingsItem(
                    modifier = Modifier.fillMaxWidth(),
                    content = stringResource(Res.string.home_settings_company),
                    icon = Icons.Outlined.Business,
                    onClick = callbacks.onCompanyClick
                )
                HorizontalDivider()
                SettingsItem(
                    modifier = Modifier.fillMaxWidth(),
                    content = stringResource(Res.string.home_settings_sign_out),
                    icon = Icons.AutoMirrored.Outlined.Logout,
                    iconTint = MaterialTheme.colorScheme.primary,
                    onClick = callbacks.onSignOutClick
                )
            }
        }
        SignOutDialog(
            onDismiss = callbacks.onCancelSignOut,
            onConfirm = callbacks.onConfirmSignOut,
            isVisible = isDialogVisible
        )
    }

    internal data class Callbacks(
        val onSignOutClick: () -> Unit,
        val onConfirmSignOut: () -> Unit,
        val onCancelSignOut: () -> Unit,
        val onAuthorizationClick: () -> Unit,
        val onProfileClick: () -> Unit,
        val onCompanyClick: () -> Unit,
    )
}
