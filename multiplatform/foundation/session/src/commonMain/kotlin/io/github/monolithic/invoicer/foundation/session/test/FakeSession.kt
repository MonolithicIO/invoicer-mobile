package io.github.monolithic.invoicer.foundation.session.test

import io.github.monolithic.invoicer.foundation.session.Session
import io.github.monolithic.invoicer.foundation.session.SessionCompany
import io.github.monolithic.invoicer.foundation.session.SessionUpdater

class FakeSession : Session {

    var companyResponse: SessionCompany = SessionCompany("", "", false)


    override fun getCompany(): SessionCompany = companyResponse
}

class FakeSessionUpdater : SessionUpdater {
    override fun updateCompany(company: SessionCompany) = Unit
}
