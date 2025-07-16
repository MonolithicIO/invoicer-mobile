package io.github.monolithic.invoicer.foundation.auth.data.model

import kotlinx.serialization.Serializable

@Serializable
internal data class RefreshRequest(
    val refreshToken: String,
)

