package com.example.aplicacionfragmentos.ui.Registro

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.aplicacionfragmentos.ui.data.AppDatabase
import com.example.aplicacionfragmentos.ui.data.User
import kotlinx.coroutines.launch

class RegistroViewModel(application: Application) : AndroidViewModel(application) {

    val registroState = MutableLiveData<RegistroState>()

    private val userDao = AppDatabase.getDatabase(application).userDao()

    init {
        insertDefaultUser()
    }

    fun registerUser(username: String, password: String) {
        viewModelScope.launch {
            val user = userDao.getUser(username, password)
            if (user != null) {
                userDao.updateLoginState(username, true)
                registroState.postValue(RegistroState.SUCCESS)
            } else {
                // Usuario no existe o error
                registroState.postValue(RegistroState.ERROR)
            }
        }
    }

    private fun insertDefaultUser() {
        viewModelScope.launch {
            // Verifica si el usuario ya existe
            val existingUser = userDao.getUser("pfer", "pfer")
            if (existingUser == null) {
                val defaultUser = User("pfer", "pfer")
                userDao.insert(defaultUser)
            }
        }
    }
    fun checkLoggedInUser() {
        viewModelScope.launch {
            val user = userDao.getLoggedInUser()
            if (user != null) {
                registroState.postValue(RegistroState.ALREADY_LOGGED_IN)
            } else {
                registroState.postValue(RegistroState.NOT_LOGGED_IN)
            }
        }
    }
    fun logoutUser() {
        viewModelScope.launch {
            userDao.logoutCurrentUser()
            registroState.postValue(RegistroState.NOT_LOGGED_IN)
        }
    }
    enum class RegistroState {
        SUCCESS,
        ERROR,
        ALREADY_LOGGED_IN,
        NOT_LOGGED_IN
    }
}
