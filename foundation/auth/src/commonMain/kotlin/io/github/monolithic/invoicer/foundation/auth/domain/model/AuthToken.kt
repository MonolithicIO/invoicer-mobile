package io.github.monolithic.invoicer.foundation.auth.domain.model

data class AuthToken(
    val accessToken: String,
    val refreshToken: String,
)
