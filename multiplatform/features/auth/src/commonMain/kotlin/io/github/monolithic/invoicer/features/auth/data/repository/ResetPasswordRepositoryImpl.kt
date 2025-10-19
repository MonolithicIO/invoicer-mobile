package io.github.monolithic.invoicer.features.auth.data.repository

import io.github.monolithic.invoicer.features.auth.data.datasource.ResetPasswordDataSource
import io.github.monolithic.invoicer.features.auth.domain.repository.ResetPasswordRepository

internal class ResetPasswordRepositoryImpl(
    private val dataSource: ResetPasswordDataSource
) : ResetPasswordRepository {

    override suspend fun requestPasswordReset(email: String) {
        return dataSource.requestPasswordReset(email)
    }

    override suspend fun verifyResetPassword(
        pinCode: String,
        requestCode: String
    ): String {
        val response = dataSource.verifyResetPassword(pinCode, requestCode)
        return response.resetToken
    }

    override suspend fun submitResetPassword(
        resetToken: String,
        newPassword: String,
        confirmPassword: String
    ) {
        return dataSource.submitResetPassword(
            resetToken,
            newPassword,
            confirmPassword
        )
    }
}