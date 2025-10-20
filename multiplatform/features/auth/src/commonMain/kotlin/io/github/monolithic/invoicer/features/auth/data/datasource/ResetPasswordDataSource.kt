package io.github.monolithic.invoicer.features.auth.data.datasource

import io.github.monolithic.invoicer.features.auth.data.model.StartResetPasswordRequest
import io.github.monolithic.invoicer.features.auth.data.model.StartResetPasswordResponse
import io.github.monolithic.invoicer.features.auth.data.model.SubmitResetPasswordRequest
import io.github.monolithic.invoicer.features.auth.data.model.VerifyResetPasswordRequest
import io.github.monolithic.invoicer.features.auth.data.model.VerifyResetPasswordResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal interface ResetPasswordDataSource {
    suspend fun requestPasswordReset(email: String): StartResetPasswordResponse
    suspend fun verifyResetPassword(
        pinCode: String,
        requestCode: String
    ): VerifyResetPasswordResponse

    suspend fun submitResetPassword(
        resetToken: String,
        newPassword: String,
        confirmPassword: String
    )
}

internal class ResetPasswordDataSourceImpl(
    private val dispatcher: CoroutineDispatcher,
    private val httpClient: HttpClient
) : ResetPasswordDataSource {

    override suspend fun requestPasswordReset(email: String): StartResetPasswordResponse {
        return withContext(dispatcher) {
            httpClient.post("/v1/user/reset_password") {
                setBody(
                    StartResetPasswordRequest(
                        email = email
                    )
                )
            }.body()
        }
    }

    override suspend fun verifyResetPassword(
        pinCode: String,
        requestCode: String
    ): VerifyResetPasswordResponse {
        return withContext(dispatcher) {
            httpClient.post("/v1/user/reset_password/$requestCode/verify") {
                setBody(
                    VerifyResetPasswordRequest(
                        pinCode = pinCode
                    )
                )
            }.body()
        }
    }

    override suspend fun submitResetPassword(
        resetToken: String,
        newPassword: String,
        confirmPassword: String
    ) {
        return withContext(dispatcher) {
            httpClient.patch("/v1/user/reset_password/$resetToken/confirm") {
                setBody(
                    SubmitResetPasswordRequest(
                        newPassword = newPassword,
                        confirmPassword = confirmPassword
                    )
                )
            }.body()
        }
    }
}
