package io.github.monolithic.invoicer.features.company.services.domain.repository

import io.github.monolithic.invoicer.features.company.services.domain.model.UpdatePayAccountModel

interface PayAccountRepository {
    suspend fun updatePayAccount(
        request: UpdatePayAccountModel,
        companyId: String,
        payAccountId: String
    )

    suspend fun deletePayAccount(
        companyId: String,
        payAccountId: String
    )
}
