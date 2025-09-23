package io.github.monolithic.invoicer.features.customer.domain.model

import kotlinx.datetime.Instant

data class CustomerListModel(
    val itemCount: Long,
    val nextPage: Long? = null,
    val items: List<CustomerListItemModel>
)

data class CustomerListItemModel(
    val id: String,
    val name: String,
    val email: String,
    val phone: String? = null,
    val companyId: String,
    val createdAt: Instant,
    val updatedAt: Instant
)
