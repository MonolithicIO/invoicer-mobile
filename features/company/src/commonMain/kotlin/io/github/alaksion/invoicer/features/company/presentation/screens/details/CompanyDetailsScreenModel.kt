package io.github.alaksion.invoicer.features.company.presentation.screens.details

import cafe.adriel.voyager.core.model.ScreenModel
import io.github.alaksion.invoicer.features.company.domain.repository.CompanyRepository
import io.github.alaksion.invoicer.foundation.session.Session
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class CompanyDetailsScreenModel(
    private val dispatcher: CoroutineDispatcher,
    private val repository: CompanyRepository,
    private val session: Session
) : ScreenModel {

    private val _state = MutableStateFlow(CompanyDetailsState())
    val state = _state.asStateFlow()

}