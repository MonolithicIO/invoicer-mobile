package io.github.monolithic.invoicer.features.auth.data.model

import kotlinx.serialization.Serializable

@Serializable
internal data class VerifyResetPasswordRequest(
    val pinCode: String
)

@Serializable
internal data class VerifyResetPasswordResponse(
    val resetToken: String
)