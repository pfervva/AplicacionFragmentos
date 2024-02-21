package com.example.aplicacionfragmentos.ui.Home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplicacionfragmentos.ui.data.AppDatabase
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val userDao = AppDatabase.getDatabase(application).userDao()

    fun logout() {
        viewModelScope.launch {
            userDao.logoutCurrentUser()
        }
    }
}
