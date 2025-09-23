package io.github.monolithic.invoicer.features.company.services.domain.model

data class ListCompaniesModel(
    val companies: List<ListCompaniesItemModel>,
    val total: Long,
    val nextPageIndex: Long?
)

data class ListCompaniesItemModel(
    val document: String,
    val name: String,
    val id: String,
)
