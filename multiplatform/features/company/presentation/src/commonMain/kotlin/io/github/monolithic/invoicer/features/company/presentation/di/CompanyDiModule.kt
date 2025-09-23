package io.github.monolithic.invoicer.features.company.presentation.di

import io.github.monolithic.invoicer.features.company.presentation.model.CreateCompanyFormManager
import io.github.monolithic.invoicer.features.company.presentation.screens.create.steps.address.CompanyAddressScreenModel
import io.github.monolithic.invoicer.features.company.presentation.screens.create.steps.confirm.ConfirmCompanyScreenModel
import io.github.monolithic.invoicer.features.company.presentation.screens.create.steps.info.CompanyInfoScreenModel
import io.github.monolithic.invoicer.features.company.presentation.screens.create.steps.payaccount.intermediary.IntermediaryPayInfoScreenModel
import io.github.monolithic.invoicer.features.company.presentation.screens.create.steps.payaccount.primary.PrimaryPayInfoScreenModel
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
            session = get()
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
        PrimaryPayInfoScreenModel(
            form = get<CreateCompanyFormManager>().getForm()
        )
    }

    factory {
        IntermediaryPayInfoScreenModel(
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
