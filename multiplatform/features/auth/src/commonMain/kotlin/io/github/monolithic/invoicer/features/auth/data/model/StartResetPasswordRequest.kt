package io.github.monolithic.invoicer.features.auth.data.model

import kotlinx.serialization.Serializable

@Serializable
internal data class StartResetPasswordRequest(
    val email: String
)

@Serializable
internal data class StartResetPasswordResponse(
    val resetToken: String
)
