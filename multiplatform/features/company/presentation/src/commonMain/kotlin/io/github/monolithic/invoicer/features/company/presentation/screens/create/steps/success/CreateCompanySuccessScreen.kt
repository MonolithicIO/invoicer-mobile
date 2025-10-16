package io.github.monolithic.invoicer.features.company.presentation.screens.create.steps.success

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.annotation.InternalVoyagerApi
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.internal.BackHandler
import invoicer.multiplatform.features.company.presentation.generated.resources.Res
import invoicer.multiplatform.features.company.presentation.generated.resources.create_company_success_cta
import invoicer.multiplatform.features.company.presentation.generated.resources.create_company_success_description
import invoicer.multiplatform.features.company.presentation.generated.resources.create_company_success_title
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.InkPrimaryButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.scaffold.InkScaffold
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.SuccessState
import org.jetbrains.compose.resources.stringResource

internal class CreateCompanySuccessScreen : Screen {

    @OptIn(InternalVoyagerApi::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        val parentNavigator = navigator?.parent

        BackHandler(enabled = true) {
            // Disable back action
        }

        InkScaffold(
            bottomBar = {
                InkPrimaryButton(
                    text = stringResource(Res.string.create_company_success_cta),
                    onClick = { parentNavigator?.pop() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(InkTheme.spacing.medium)
                        .navigationBarsPadding()
                )
            }
        ) { scaffoldPadding ->
            SuccessState(
                title = stringResource(Res.string.create_company_success_title),
                description = stringResource(Res.string.create_company_success_description),
                modifier = Modifier
                    .padding(scaffoldPadding)
                    .padding(InkTheme.spacing.medium)
                    .fillMaxSize()
                    .statusBarsPadding()
            )
        }
    }

}
