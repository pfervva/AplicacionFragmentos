package com.example.aplicacionfragmentos.ui.Registro

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.aplicacionfragmentos.utils.Constants
import kotlinx.coroutines.launch

class RegistroViewModel(private val sharedPreferences: SharedPreferences) : ViewModel() {

    val registroState = MutableLiveData<RegistroState>()

    init {
        checkIfUserIsLoggedIn()
    }

    fun registerUser(username: String, password: String) {
        viewModelScope.launch {
            // Aquí usamos Constants.USUARIO y Constants.CONTRASEÑA para la comparación
            if (username == Constants.USUARIO && password == Constants.CONTRASEÑA) {
                with(sharedPreferences.edit()) {
                    putBoolean(Constants.IS_LOGIN, true)
                    putString(Constants.USUARIO, username)
                    putString(Constants.CONTRASEÑA, password)
                    apply()
                }
                registroState.postValue(RegistroState.SUCCESS)
            } else {
                registroState.postValue(RegistroState.ERROR)
            }
        }
    }

    private fun checkIfUserIsLoggedIn() {
        val isLogged = sharedPreferences.getBoolean(Constants.IS_LOGIN, false)
        registroState.postValue(if (isLogged) RegistroState.ALREADY_LOGGED_IN else RegistroState.NOT_LOGGED_IN)
    }

    enum class RegistroState {
        SUCCESS,
        ERROR,
        ALREADY_LOGGED_IN,
        NOT_LOGGED_IN
    }
}
