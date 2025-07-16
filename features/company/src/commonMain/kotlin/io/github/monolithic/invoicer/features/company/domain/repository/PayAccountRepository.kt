package io.github.monolithic.invoicer.features.company.domain.repository

import io.github.monolithic.invoicer.features.company.domain.model.UpdatePayAccountModel

internal interface PayAccountRepository {
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
