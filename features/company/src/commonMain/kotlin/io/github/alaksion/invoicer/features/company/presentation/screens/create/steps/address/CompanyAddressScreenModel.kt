package io.github.alaksion.invoicer.features.company.presentation.screens.create.steps.address

import cafe.adriel.voyager.core.model.ScreenModel
import io.github.alaksion.invoicer.features.company.presentation.model.CreateCompanyForm

internal class CompanyAddressScreenModel(
    private val form: CreateCompanyForm
) : ScreenModel {

    fun setName(name: String) {
        form.companyName = name
    }
}