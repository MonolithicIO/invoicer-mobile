package io.github.monolithic.invoicer.features.company.presentation.di

import io.github.monolithic.invoicer.features.company.presentation.model.CreateCompanyFormManager
import io.github.monolithic.invoicer.features.company.presentation.screens.create.steps.address.CompanyAddressScreenModel
import io.github.monolithic.invoicer.features.company.presentation.screens.create.steps.confirm.ConfirmCompanyScreenModel
import io.github.monolithic.invoicer.features.company.presentation.screens.create.steps.info.CompanyInfoScreenModel
import io.github.monolithic.invoicer.features.company.presentation.screens.create.steps.intermediaryaccount.IntermediaryAccountScreenModel
import io.github.monolithic.invoicer.features.company.presentation.screens.create.steps.payaccount.PayAccountScreenModel
import io.github.monolithic.invoicer.features.company.presentation.screens.details.CompanyDetailsScreenModel
import io.github.monolithic.invoicer.features.company.presentation.screens.select.SelectCompanyScreenModel
import io.github.monolithic.invoicer.features.company.presentation.screens.updateaddress.UpdateAddressScreenModel
import io.github.monolithic.invoicer.features.company.presentation.screens.updatepayaccount.UpdatePayAccountScreenModel
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val companyPresentationDiModule = module {

    factory<SelectCompanyScreenModel> {
        SelectCompanyScreenModel(
            dispatcher = Dispatchers.Default,
            repository = get(),
            selectCompanyService = get()
        )
    }

    factory {
        CompanyInfoScreenModel(
            form = get<CreateCompanyFormManager>().getForm()
        )
    }

    factory {
        CompanyAddressScreenModel(
            form = get<CreateCompanyFormManager>().getForm()
        )
    }

    factory {
        ConfirmCompanyScreenModel(
            form = get<CreateCompanyFormManager>().getForm(),
            dispatcher = Dispatchers.Default,
            repository = get()
        )
    }

    factory {
        PayAccountScreenModel(
            form = get<CreateCompanyFormManager>().getForm()
        )
    }

    factory {
        IntermediaryAccountScreenModel(
            form = get<CreateCompanyFormManager>().getForm()
        )
    }

    single<CreateCompanyFormManager> {
        CreateCompanyFormManager()
    }

    factory {
        CompanyDetailsScreenModel(
            dispatcher = Dispatchers.Default,
            repository = get(),
            session = get()
        )
    }


    factory {
        UpdatePayAccountScreenModel(
            repository = get(),
            dispatcher = Dispatchers.Default,
            session = get()
        )
    }

    factory {
        UpdateAddressScreenModel(
            companyRepository = get(),
            dispatcher = Dispatchers.Default,
            session = get()
        )
    }
}
