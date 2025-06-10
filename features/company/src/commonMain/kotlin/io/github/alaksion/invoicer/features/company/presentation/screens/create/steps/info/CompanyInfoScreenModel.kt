package io.github.alaksion.invoicer.features.company.presentation.screens.create.steps.info

import cafe.adriel.voyager.core.model.ScreenModel
import io.github.alaksion.invoicer.features.company.presentation.model.CreateCompanyForm
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

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
        _state.update {
            it.copy(
                companyName = name
            )
        }
        form.companyName = name
    }

    fun setDocument(document: String) {
        _state.update {
            it.copy(
                companyDocument = document
            )
        }
        form.companyDocument = document
    }
}