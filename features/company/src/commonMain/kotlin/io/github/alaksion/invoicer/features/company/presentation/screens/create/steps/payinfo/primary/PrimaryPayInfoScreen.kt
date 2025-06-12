package io.github.alaksion.invoicer.features.company.presentation.screens.create.steps.payinfo.primary

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen

internal class PrimaryPayInfoScreen : Screen {

    @Composable
    override fun Content() {

    }

    @Composable
    internal fun StateContent(
        state: PrimaryPayInfoState,
    ) {

    }

    data class Callbacks(
        val onChangeIban: (String) -> Unit,
        val onChangeSwift: (String) -> Unit,
        val onChangeBankName: (String) -> Unit,
        val onChangeBankAddress: (String) -> Unit,
    )

}