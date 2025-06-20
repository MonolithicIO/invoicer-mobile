package io.github.alaksion.invoicer.foundation.session

interface Session {
    var tokens: SessionTokens?
    var company: SessionCompany
}

internal object SessionImpl : Session {
    override var tokens: SessionTokens? = null
    override var company: SessionCompany = SessionCompany("", "", false)
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
