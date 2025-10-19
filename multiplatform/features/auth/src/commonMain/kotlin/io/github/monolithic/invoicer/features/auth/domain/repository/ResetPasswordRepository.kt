package io.github.monolithic.invoicer.features.auth.domain.repository

internal interface ResetPasswordRepository {

    suspend fun requestPasswordReset(email: String)

    suspend fun verifyResetPassword(
        pinCode: String,
        requestCode: String
    ): String

    suspend fun submitResetPassword(
        resetToken: String,
        newPassword: String,
        confirmPassword: String
    )
}
