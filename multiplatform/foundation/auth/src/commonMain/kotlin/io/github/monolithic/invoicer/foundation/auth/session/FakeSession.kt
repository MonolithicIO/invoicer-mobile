package io.github.monolithic.invoicer.foundation.auth.session

class FakeSession : Session {

    var companyResponse: SessionCompany = SessionCompany("", "",)


    override fun getCompany(): SessionCompany = companyResponse
}

class FakeSessionUpdater : SessionUpdater {
    override fun updateCompany(company: SessionCompany) = Unit
}
