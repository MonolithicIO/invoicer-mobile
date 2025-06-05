package io.github.alaksion.invoicer.features.company.di

import io.github.alaksion.invoicer.features.company.data.datasource.CompanyDataSource
import io.github.alaksion.invoicer.features.company.data.datasource.CompanyDataSourceImpl
import io.github.alaksion.invoicer.features.company.data.repository.CompanyRepositoryImpl
import io.github.alaksion.invoicer.features.company.domain.repository.CompanyRepository
import org.koin.dsl.module

val companyDiModule = module {
    factory<CompanyDataSource> { CompanyDataSourceImpl() }

    factory<CompanyRepository> { CompanyRepositoryImpl() }
}