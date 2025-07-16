package io.github.monolithic.invoicer.foundation.network.di

import io.github.monolithic.invoicer.foundation.network.client.HttpWrapper
import org.koin.dsl.module

val networkDiModule = module {
    single { HttpWrapper }
}
