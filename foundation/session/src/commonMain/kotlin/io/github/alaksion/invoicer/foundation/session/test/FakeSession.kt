package io.github.alaksion.invoicer.foundation.session.test

import io.github.alaksion.invoicer.foundation.session.Session
import io.github.alaksion.invoicer.foundation.session.SessionCompany
import io.github.alaksion.invoicer.foundation.session.SessionTokens
import io.github.alaksion.invoicer.foundation.session.SessionUpdater

class FakeSession : Session {
    var tokens: SessionTokens? = null
    var company: SessionCompany = SessionCompany("", "", false)

    override fun getTokens(): SessionTokens? = tokens

    override fun getCompany(): SessionCompany = company
}

class FakeSessionUpdater : SessionUpdater {
    override fun updateTokens(tokens: SessionTokens) = Unit

    override fun updateCompany(company: SessionCompany) = Unit
}
