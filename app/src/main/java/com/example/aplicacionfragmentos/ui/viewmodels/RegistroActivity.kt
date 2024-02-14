package com.example.aplicacionfragmentos.ui.viewmodels

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.aplicacionfragmentos.Home
import com.example.aplicacionfragmentos.R
import com.example.aplicacionfragmentos.utils.Constants

class RegistroActivity : AppCompatActivity() {

    private val viewModel: RegistroViewModel by viewModels {
        RegistroViewModelFactory(getSharedPreferences(Constants.PREFS_NAME, MODE_PRIVATE))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        viewModel.registroState.observe(this) { state ->
            when (state) {
                RegistroViewModel.RegistroState.SUCCESS -> goToHome()
                RegistroViewModel.RegistroState.ALREADY_LOGGED_IN -> goToHome()
                RegistroViewModel.RegistroState.ERROR -> showErrorToast()
                RegistroViewModel.RegistroState.NOT_LOGGED_IN -> { /* No action needed */ }
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
}
