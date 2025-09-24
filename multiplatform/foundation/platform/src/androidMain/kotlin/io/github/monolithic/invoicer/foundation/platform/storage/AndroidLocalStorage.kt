package io.github.monolithic.invoicer.foundation.platform.storage

import android.annotation.SuppressLint
import android.content.Context
import androidx.core.content.edit

internal class AndroidLocalStorage(
    private val context: Context
) : LocalStorage {

    private val preferences by lazy {
        context.getSharedPreferences("invoicer", Context.MODE_PRIVATE)
    }

    @SuppressLint("UseKtx")
    // edit {} does not work
    override fun setString(value: String, key: String) {
        preferences.edit().putString(key, value).apply()

    }

    override fun getString(key: String): String? {
        return preferences.getString(key, null)
    }

    override fun clear(key: String) {
        preferences.edit { remove(key) }
    }
}
