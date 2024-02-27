package com.example.aplicacionfragmentos.ui.Registro.inicio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aplicacionfragmentos.R
import com.example.aplicacionfragmentos.RetroFit.ApiClient
import com.example.aplicacionfragmentos.ui.Registro.Registro.RegistroActivity
import kotlinx.coroutines.*

class InicioSesionActivity : AppCompatActivity() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_sesion)

        val buttonRegister: Button = findViewById(R.id.buttonlogin)
        buttonRegister.setOnClickListener {
            val email = findViewById<EditText>(R.id.editText_email_signin).text.toString()
            val password = findViewById<EditText>(R.id.editText_password_signin).text.toString()
            val nombre = findViewById<EditText>(R.id.EditText_username).text.toString() // Verifica si realmente necesitas el nombre para el registro
            registrarUsuario(email, password, nombre, "1")
        }

        val textViewLogin: TextView = findViewById(R.id.textView7)
        textViewLogin.setOnClickListener {
            val loginIntent = Intent(this, RegistroActivity::class.java)
            startActivity(loginIntent)
        }
    }

    private fun registrarUsuario(email: String, password: String, nombre: String, disponible: String) {
        coroutineScope.launch {
            val response = withContext(Dispatchers.IO) {
                ApiClient.instance.registrarUsuario(InicioSesionRequest(email, password, nombre, disponible))
            }

            if (response.isSuccessful && response.body()?.result == "ok") {
                Toast.makeText(this@InicioSesionActivity, "Registro exitoso. Por favor, inicie sesión.", Toast.LENGTH_LONG).show()
                // En lugar de ir a Home, ahora va a RegistroActivity para iniciar sesión
                goToLogin()
            } else {
                Toast.makeText(this@InicioSesionActivity, "Error en el registro", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun goToLogin() {
        val loginIntent = Intent(this, RegistroActivity::class.java)
        startActivity(loginIntent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
    }
}
