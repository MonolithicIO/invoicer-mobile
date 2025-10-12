package io.github.monolithic.invoicer.features.company.presentation.screens.create.steps.info

import cafe.adriel.voyager.core.model.ScreenModel
import io.github.monolithic.invoicer.features.company.presentation.model.CreateCompanyForm
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet

internal class CompanyInfoScreenModel(
    private val form: CreateCompanyForm
) : ScreenModel {

    private val _state = MutableStateFlow(CompanyInfoState())
    val state = _state.asStateFlow()

    fun resumeState() {
        _state.update {
            it.copy(
                companyName = form.companyName,
                companyDocument = form.companyDocument
            )
        }
    }

    fun setName(name: String) {
        _state.updateAndGet {
            it.copy(
                companyName = name.trim()
            )
        }.let { newState ->
            form.companyName = newState.companyName
        }

    }

    fun setDocument(document: String) {
        _state.updateAndGet {
            it.copy(
                companyDocument = document.trim()
            )
        }.let { newState ->
            form.companyDocument = newState.companyDocument
        }
    }
}
