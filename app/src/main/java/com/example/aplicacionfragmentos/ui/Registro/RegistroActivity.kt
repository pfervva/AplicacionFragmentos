package com.example.aplicacionfragmentos.ui.Registro

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.aplicacionfragmentos.more.Home
import com.example.aplicacionfragmentos.R
import com.example.aplicacionfragmentos.utils.Constants

class RegistroActivity : AppCompatActivity() {

    private val viewModel: RegistroViewModel by viewModels {
        RegistroViewModelFactory(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        //Aqui verifico si esta logueado o no
        viewModel.checkLoggedInUser()

        viewModel.registroState.observe(this) { state ->
            when (state) {
                RegistroViewModel.RegistroState.SUCCESS -> goToHome()
                RegistroViewModel.RegistroState.ALREADY_LOGGED_IN -> goToHome()
                RegistroViewModel.RegistroState.ERROR -> showErrorToast()
                RegistroViewModel.RegistroState.NOT_LOGGED_IN -> { }
            }
        }

        val buttonLogin: Button = findViewById(R.id.buttonlogin)
        buttonLogin.setOnClickListener {
            val enteredUsername = findViewById<EditText>(R.id.editText_login).text.toString()
            val enteredPassword = findViewById<EditText>(R.id.editText_password).text.toString()
            viewModel.registerUser(enteredUsername, enteredPassword)
        }
    }

    private fun goToHome() {
        startActivity(Intent(this, Home::class.java))
        finish()
    }

    private fun showErrorToast() {
        Toast.makeText(this, "Nombre de usuario o contraseña erróneo", Toast.LENGTH_SHORT).show()
    }

//Pone registro pero es login, lo tengo intercambiados por que me equivoque desde el principio
}
