package io.github.monolithic.invoicer.foundation.platform.firebaseAuth

interface IosGoogleFirebaseHelper {
    suspend fun getGoogleIdToken(): IosGoogleResult
}

sealed interface IosGoogleResult {
    data class Success(val token: String) : IosGoogleResult
    data class Error(val exception: Exception) : IosGoogleResult
    data object Cancelled : IosGoogleResult
}
