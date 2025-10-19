package io.github.monolithic.invoicer.features.auth.data.model

import kotlinx.serialization.Serializable

@Serializable
internal data class SubmitResetPasswordRequest(
    val newPassword: String,
    val confirmPassword: String
)
