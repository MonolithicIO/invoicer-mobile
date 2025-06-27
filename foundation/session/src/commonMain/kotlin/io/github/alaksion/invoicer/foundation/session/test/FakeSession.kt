package io.github.alaksion.invoicer.foundation.session.test

import io.github.alaksion.invoicer.foundation.session.Session
import io.github.alaksion.invoicer.foundation.session.SessionCompany
import io.github.alaksion.invoicer.foundation.session.SessionTokens

class FakeSession : Session {
    override var tokens: SessionTokens? = null
    override var company: SessionCompany = SessionCompany("", "", false)
}
