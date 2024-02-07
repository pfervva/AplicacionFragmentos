package com.example.aplicacionfragmentos

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegistroActivity : AppCompatActivity() {

    companion object {
        const val MYUSER = "" // Actualiza esto con tus credenciales
        const val MYPASS = "" // Actualiza esto con tus credenciales
        const val PREFS_NAME = "MyPrefsFile"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        // Verificar si ya ha iniciado sesión
        val sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        if (sharedPreferences.getBoolean("isLoggedIn", false)) {
            // Usuario ya ha iniciado sesión, redirigir a Home
            startActivity(Intent(this, Home::class.java))
            finish()
        }

        val textView7: TextView = findViewById(R.id.textView7)
        val buttonLogin: Button = findViewById(R.id.buttonlogin)

        textView7.setOnClickListener {
            startActivity(Intent(this, InicioSesionActivity::class.java))
        }

        buttonLogin.setOnClickListener {
            val enteredUsername = findViewById<EditText>(R.id.editText_login).text.toString()
            val enteredPassword = findViewById<EditText>(R.id.editText_password).text.toString()

            if (enteredUsername == MYUSER && enteredPassword == MYPASS) {
                // Guardar estado de inicio de sesión
                sharedPreferences.edit().apply {
                    putBoolean("isLoggedIn", true)
                    apply()
                }

                startActivity(Intent(this, Home::class.java))
                finish()
            } else {
                Toast.makeText(this, "Nombre de usuario o contraseña erroneo", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
