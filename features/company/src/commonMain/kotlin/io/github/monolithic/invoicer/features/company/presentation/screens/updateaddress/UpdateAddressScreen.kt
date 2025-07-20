package io.github.monolithic.invoicer.features.company.presentation.screens.updateaddress

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import kotlinx.serialization.Serializable

internal class UpdateAddressScreen(
    private val args: Args
) : Screen {

    @Composable
    override fun Content() {
        TODO("Not yet implemented")
    }

    @Serializable
    data class Args(
        val addressLine: String = "",
        val addressLine2: String? = "",
        val city: String = "",
        val state: String = "",
        val postalCode: String = "",
    )
}