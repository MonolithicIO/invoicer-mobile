package io.github.monolithic.invoicer.foundation.network

import kotlinx.serialization.Serializable

sealed class RequestError : Throwable() {
    data class Http(
        val httpCode: Int,
        val errorCode: Int? = null,
        override val message: String?
    ) : RequestError()

    data class Other(
        val throwable: Throwable
    ) : RequestError()
}

@Serializable
internal data class InvoicerHttpError(
    val message: String,
    val timeStamp: String,
    val errorCode: Int = 0
)

