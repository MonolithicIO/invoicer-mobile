package io.github.monolithic.invoicer.features.customer.data.model

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
internal data class CustomerListResponse(
    val itemCount: Long,
    val nextPage: Long? = null,
    val items: List<CustomerListItemResponse>
)

@Serializable
internal data class CustomerListItemResponse(
    val id: String,
    val name: String,
    val email: String,
    val phone: String? = null,
    val companyId: String,
    val createdAt: Instant,
    val updatedAt: Instant
)
