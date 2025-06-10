package io.github.alaksion.invoicer.features.company.presentation.screens.create.steps.address

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel


internal class CompanyAddressStep : Screen {

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<CompanyAddressScreenModel>()
        StateContent()
    }

    @Composable
    fun StateContent(

    ) {
        Scaffold {

        }
    }
}