package io.github.alaksion.invoicer.foundation.session.test

import io.github.alaksion.invoicer.foundation.session.Session
import io.github.alaksion.invoicer.foundation.session.SessionCompany
import io.github.alaksion.invoicer.foundation.session.SessionTokens
import io.github.alaksion.invoicer.foundation.session.SessionUpdater

class FakeSession : Session {

    var tokensResponse: SessionTokens? = null
    var companyResponse: SessionCompany = SessionCompany("", "", false)

    override fun getTokens(): SessionTokens? = tokensResponse

    override fun getCompany(): SessionCompany = companyResponse
}

class FakeSessionUpdater : SessionUpdater {
    override fun updateTokens(tokens: SessionTokens) = Unit
    override fun updateCompany(company: SessionCompany) = Unit
    override fun clearTokens() = Unit
}
