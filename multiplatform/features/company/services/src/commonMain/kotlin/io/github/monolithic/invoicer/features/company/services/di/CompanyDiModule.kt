package io.github.monolithic.invoicer.features.company.services.di

import io.github.monolithic.invoicer.features.company.services.data.datasource.CompanyLocalDatasource
import io.github.monolithic.invoicer.features.company.services.data.datasource.CompanyLocalDatasourceImpl
import io.github.monolithic.invoicer.features.company.services.data.datasource.CompanyRemoteDataSource
import io.github.monolithic.invoicer.features.company.services.data.datasource.CompanyRemoteDataSourceImpl
import io.github.monolithic.invoicer.features.company.services.data.datasource.PayAccountRemoteDataSource
import io.github.monolithic.invoicer.features.company.services.data.datasource.PayAccountRemoteDataSourceImpl
import io.github.monolithic.invoicer.features.company.services.data.repository.CompanyRepositoryImpl
import io.github.monolithic.invoicer.features.company.services.data.repository.PayAccountRepositoryImpl
import io.github.monolithic.invoicer.features.company.services.domain.repository.CompanyRepository
import io.github.monolithic.invoicer.features.company.services.domain.repository.PayAccountRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.dsl.module

val companyServicesDiModule = module {
    factory<CompanyRemoteDataSource> {
        CompanyRemoteDataSourceImpl(
            dispatcher = Dispatchers.IO,
            httpWrapper = get()
        )
    }

    factory<CompanyLocalDatasource> {
        CompanyLocalDatasourceImpl(
            storage = get()
        )
    }

    factory<CompanyRepository> {
        CompanyRepositoryImpl(
            remoteDatasource = get(),
            localDatasource = get()
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
