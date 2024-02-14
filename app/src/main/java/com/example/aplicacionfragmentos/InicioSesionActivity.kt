package com.example.aplicacionfragmentos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.aplicacionfragmentos.ui.Registro.RegistroActivity

class InicioSesionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_sesion)
        val textView7: TextView = findViewById(R.id.textView7)
        textView7.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)

        }
    }
}