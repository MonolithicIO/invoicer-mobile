package io.github.alaksion.invoicer.features.company.presentation.screens.create.steps.confirm

import cafe.adriel.voyager.core.model.ScreenModel
import io.github.alaksion.invoicer.features.company.presentation.model.CreateCompanyForm
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class ConfirmCompanyScreenModel(
    private val form: CreateCompanyForm
) : ScreenModel {

    private val _state = MutableStateFlow(ConfirmCompanyState())
    val state = _state.asStateFlow()

}