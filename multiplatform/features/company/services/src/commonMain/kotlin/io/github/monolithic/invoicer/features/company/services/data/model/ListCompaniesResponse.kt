package io.github.monolithic.invoicer.features.company.services.data.model

import kotlinx.serialization.Serializable

@Serializable
internal data class ListCompaniesResponse(
    val companies: List<ListCompaniesItemResponse>,
    val total: Long,
    val nextPageIndex: Long?
)

@Serializable
internal data class ListCompaniesItemResponse(
    val document: String,
    val name: String,
    val id: String,
)
