package io.github.monolithic.invoicer.foundation.session

interface Session {
    fun getTokens(): SessionTokens?
    fun getCompany(): SessionCompany
}

interface SessionUpdater {
    fun updateTokens(tokens: SessionTokens)

    fun updateCompany(company: SessionCompany)

    fun clearTokens()
}

internal object SessionImpl : Session, SessionUpdater {
    private var tokens: SessionTokens? = null
    private var company: SessionCompany = SessionCompany("", "", false)

    override fun getTokens(): SessionTokens? = tokens

    override fun getCompany(): SessionCompany = company

    override fun updateTokens(tokens: SessionTokens) {
        this.tokens = tokens
    }

    override fun updateCompany(company: SessionCompany) {
        this.company = company
    }

    override fun clearTokens() {
        this.tokens = null
    }
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
