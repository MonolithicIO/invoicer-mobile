package io.github.alaksion.invoicer.features.company.di

import io.github.alaksion.invoicer.features.company.data.datasource.CompanyRemoteDataSource
import io.github.alaksion.invoicer.features.company.data.datasource.CompanyRemoteDataSourceImpl
import io.github.alaksion.invoicer.features.company.data.repository.CompanyRepositoryImpl
import io.github.alaksion.invoicer.features.company.domain.repository.CompanyRepository
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
}