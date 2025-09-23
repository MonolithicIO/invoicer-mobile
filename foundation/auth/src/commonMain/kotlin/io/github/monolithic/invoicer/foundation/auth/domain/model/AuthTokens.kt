package io.github.monolithic.invoicer.foundation.auth.domain.model

data class AuthTokens(
    val accessToken: String,
    val refreshToken: String,
)
