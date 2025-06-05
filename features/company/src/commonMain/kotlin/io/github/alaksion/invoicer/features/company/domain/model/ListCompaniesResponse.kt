package io.github.alaksion.invoicer.features.company.domain.model

internal data class ListCompaniesModel(
    val companies: List<ListCompaniesItemModel>,
    val total: Long,
    val nextPageIndex: Long?
)

internal data class ListCompaniesItemModel(
    val document: String,
    val name: String,
    val id: String,
)