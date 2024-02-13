package com.example.aplicacionfragmentos

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegistroActivity : AppCompatActivity() {

    private lateinit var preferences: SharedPreferences

    companion object {
        const val PREFS_NAME = "Preferencias"
        const val IS_LOGIN = "IsLogged"
        const val USUARIO = "usuario"
        const val CONTRASEÑA = "password"
        const val MYUSER = ""
        const val MYPASS = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)

        // Comprueba si el usuario ya está logueado
        if (preferences.getBoolean(IS_LOGIN, false)) {
            goToHome()
        }

        val textView7: TextView = findViewById(R.id.textView7)
        val buttonLogin: Button = findViewById(R.id.buttonlogin)

        textView7.setOnClickListener {
            goToLogin()
        }

        buttonLogin.setOnClickListener {
            val enteredUsername = findViewById<EditText>(R.id.editText_login).text.toString()
            val enteredPassword = findViewById<EditText>(R.id.editText_password).text.toString()

            if (enteredUsername == MYUSER && enteredPassword == MYPASS) {
                preferences.edit().apply {
                    putBoolean(IS_LOGIN, true)
                    putString(USUARIO, enteredUsername)
                    putString(CONTRASEÑA, enteredPassword)
                    apply()
                }
                goToHome()
            } else {
                Toast.makeText(this, "Nombre de usuario o contraseña erróneo", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun goToHome() {
        val intent = Intent(this, Home::class.java)
        startActivity(intent)
        finish()
    }

    private fun goToLogin() {
        Toast.makeText(this, "Redireccionando a la pantalla de inicio de sesión...", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, InicioSesionActivity::class.java)
        startActivity(intent)
    }
}
