package io.github.monolithic.invoicer.foundation.platform.firebaseAuth

import com.google.firebase.auth.FirebaseAuth

internal class AndroidFirebaseHelper(
    private val firebaseAuth: FirebaseAuth
) : FirebaseHelper {
    override fun signOut() {
        firebaseAuth.signOut()
    }
}
