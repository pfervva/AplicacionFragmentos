package com.example.aplicacionfragmentos.ui.Registro.Registro

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aplicacionfragmentos.R
import com.example.aplicacionfragmentos.RetroFit.ApiClient
import com.example.aplicacionfragmentos.RetroFit.LoginRequest
import com.example.aplicacionfragmentos.RetroFit.PreferenceHelper
import com.example.aplicacionfragmentos.more.Home
import com.example.aplicacionfragmentos.ui.Registro.inicio.InicioSesionActivity
import kotlinx.coroutines.*

class RegistroActivity : AppCompatActivity() {

    private val activityScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        checkAuthToken()

        val buttonLogin: Button = findViewById(R.id.buttonlogin)
        buttonLogin.setOnClickListener {
            val enteredEmail = findViewById<EditText>(R.id.editText_login).text.toString()
            val enteredPassword = findViewById<EditText>(R.id.editText_password).text.toString()
            loginUser(enteredEmail, enteredPassword)
        }

        // Configura el TextView para que actúe como botón de redirección al registro (LoginActivity)
        val textViewRegister: TextView = findViewById(R.id.textView7)
        textViewRegister.setOnClickListener {
            // Inicia LoginActivity, que es tu pantalla de registro
            val loginIntent = Intent(this, InicioSesionActivity::class.java)
            startActivity(loginIntent)
        }
    }

    private fun checkAuthToken() {
        val token = PreferenceHelper.getAuthToken(this)
        if (token != null && token.isNotEmpty()) {
            goToHome()
        }
        // Si no hay token, el usuario debe iniciar sesión, por lo que permanece en esta actividad
    }

    private fun loginUser(email: String, password: String) {
        activityScope.launch {
            val response = withContext(Dispatchers.IO) {
                ApiClient.instance.loginUser(LoginRequest(email, password))
            }

            if (response.isSuccessful && response.body()?.result == "ok") {
                val token = response.body()?.token ?: ""
                PreferenceHelper.saveAuthToken(this@RegistroActivity, token)
                goToHome()
            } else {
                showErrorToast()
            }
        }
    }

    private fun goToHome() {
        val intent = Intent(this, Home::class.java)
        startActivity(intent)
        finish()
    }

    private fun showErrorToast() {
        Toast.makeText(this, "Error en inicio de sesión", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        activityScope.cancel()
    }
}
