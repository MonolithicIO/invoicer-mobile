package io.github.alaksion.invoicer.foundation.session

object Session {
    var tokens: SessionTokens? = null
    var company: SessionCompany = SessionCompany("", "", false)
}

data class SessionTokens(
    val accessToken: String,
    val refreshToken: String,
)

data class SessionCompany(
    val id: String,
    val name: String,
    val isChangeCompanyEnabled: Boolean
)
