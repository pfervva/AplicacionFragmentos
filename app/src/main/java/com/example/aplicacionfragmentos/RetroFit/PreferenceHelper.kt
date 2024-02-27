package com.example.aplicacionfragmentos.RetroFit

import android.content.Context
import android.content.SharedPreferences

object PreferenceHelper {
    private const val PREFS_NAME = "prefs"
    private const val TOKEN_KEY = "token"

    private fun preferences(context: Context): SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveAuthToken(context: Context, token: String) {
        preferences(context).edit().putString(TOKEN_KEY, token).apply()
    }

    fun getAuthToken(context: Context): String? =
        preferences(context).getString(TOKEN_KEY, null)

    fun clearAuthToken(context: Context) {
        preferences(context).edit().remove(TOKEN_KEY).apply()
    }
}
