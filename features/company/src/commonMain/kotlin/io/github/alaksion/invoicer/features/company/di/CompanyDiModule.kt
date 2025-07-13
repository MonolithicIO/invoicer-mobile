package io.github.alaksion.invoicer.features.company.di

import io.github.alaksion.invoicer.features.company.data.datasource.CompanyRemoteDataSource
import io.github.alaksion.invoicer.features.company.data.datasource.CompanyRemoteDataSourceImpl
import io.github.alaksion.invoicer.features.company.data.datasource.PayAccountRemoteDataSource
import io.github.alaksion.invoicer.features.company.data.datasource.PayAccountRemoteDataSourceImpl
import io.github.alaksion.invoicer.features.company.data.repository.CompanyRepositoryImpl
import io.github.alaksion.invoicer.features.company.data.repository.PayAccountRepositoryImpl
import io.github.alaksion.invoicer.features.company.domain.repository.CompanyRepository
import io.github.alaksion.invoicer.features.company.domain.repository.PayAccountRepository
import io.github.alaksion.invoicer.features.company.presentation.model.CreateCompanyFormManager
import io.github.alaksion.invoicer.features.company.presentation.screens.create.steps.address.CompanyAddressScreenModel
import io.github.alaksion.invoicer.features.company.presentation.screens.create.steps.confirm.ConfirmCompanyScreenModel
import io.github.alaksion.invoicer.features.company.presentation.screens.create.steps.info.CompanyInfoScreenModel
import io.github.alaksion.invoicer.features.company.presentation.screens.create.steps.payaccount.intermediary.IntermediaryPayInfoScreenModel
import io.github.alaksion.invoicer.features.company.presentation.screens.create.steps.payaccount.primary.PrimaryPayInfoScreenModel
import io.github.alaksion.invoicer.features.company.presentation.screens.details.CompanyDetailsScreenModel
import io.github.alaksion.invoicer.features.company.presentation.screens.select.SelectCompanyScreenModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.dsl.module

val companyDiModule = module {
    factory<CompanyRemoteDataSource> {
        CompanyRemoteDataSourceImpl(
            dispatcher = Dispatchers.IO,
            httpWrapper = get()
        )
    }

    factory<CompanyRepository> {
        CompanyRepositoryImpl(
            dataSource = get()
        )
    }

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

    factory<PayAccountRemoteDataSource> {
        PayAccountRemoteDataSourceImpl(
            httpWrapper = get(),
            dispatcher = Dispatchers.IO
        )
    }

    factory<PayAccountRepository> {
        PayAccountRepositoryImpl(
            dataSource = get()
        )
    }
}
