package com.example.aplicacionfragmentos.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.aplicacionfragmentos.utils.Constants.PREFS_NAME

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    fun logout() {
        // Obtiene las SharedPreferences usando el contexto de la aplicación
        val preferences = getApplication<Application>().getSharedPreferences(PREFS_NAME, Application.MODE_PRIVATE)
        preferences.edit().apply {
            clear()
            apply()
        }
    }
}
