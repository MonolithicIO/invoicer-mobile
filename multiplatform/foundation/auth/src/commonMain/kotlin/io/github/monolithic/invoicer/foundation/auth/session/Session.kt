package io.github.monolithic.invoicer.foundation.auth.session

interface Session {
    fun getCompany(): SessionCompany
}

interface SessionUpdater {
    fun updateCompany(company: SessionCompany)
}

internal object SessionImpl : Session, SessionUpdater {
    private var company: SessionCompany = SessionCompany("", "", false)


    override fun getCompany(): SessionCompany = company

    override fun updateCompany(company: SessionCompany) {
        this.company = company
    }

}

data class SessionCompany(
    val id: String,
    val name: String,
    val isChangeCompanyEnabled: Boolean
)
