package com.example.aplicacionfragmentos.ui.Registro.inicio

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.aplicacionfragmentos.R
import com.example.aplicacionfragmentos.RetroFit.ApiClient
import com.example.aplicacionfragmentos.ui.Registro.Registro.RegistroActivity
import kotlinx.coroutines.*
import java.io.ByteArrayOutputStream

class InicioSesionActivity : AppCompatActivity() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private val CAMERA_REQUEST_CODE = 101
    private val GALLERY_REQUEST_CODE = 102
    private val PERMISSIONS_REQUEST_CODE = 123
    private val REQUIRED_PERMISSIONS = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    private var imageBase64: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_sesion)

        if (!hasAllPermissionsGranted()) {
            requestNecessaryPermissions()
        }

        val buttonAddPhoto: Button = findViewById(R.id.buttonAddPhoto)
        buttonAddPhoto.setOnClickListener {
            if (hasAllPermissionsGranted()) {
                showImagePickerOptions()
            } else {
                requestNecessaryPermissions()
            }
        }

        val buttonRegister: Button = findViewById(R.id.buttonlogin)
        buttonRegister.setOnClickListener {
            val email = findViewById<EditText>(R.id.editText_email_signin).text.toString()
            val password = findViewById<EditText>(R.id.editText_password_signin).text.toString()
            val nombre = findViewById<EditText>(R.id.EditText_username).text.toString() // Verifica si realmente necesitas el nombre para el registro
            registrarUsuario(email, password, nombre, "1", imageBase64)
        }

        val textViewLogin: TextView = findViewById(R.id.textView7)
        textViewLogin.setOnClickListener {
            val loginIntent = Intent(this, RegistroActivity::class.java)
            startActivity(loginIntent)
        }
    }

    fun showImagePickerOptions() {
        val options = arrayOf<CharSequence>("Tomar foto", "Elegir de galería", "Cancelar")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Añadir foto")
        builder.setItems(options) { dialog, item ->
            when (options[item]) {
                "Tomar foto" -> openCamera()
                "Elegir de galería" -> openGallery()
                "Cancelar" -> dialog.dismiss()
            }
        }
        builder.show()
    }

    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE)
    }

    private fun openGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                CAMERA_REQUEST_CODE -> {
                    val photo = data?.extras?.get("data") as Bitmap
                    imageBase64 = convertBitmapToBase64(photo)
                }
                GALLERY_REQUEST_CODE -> {
                    val imageUri = data?.data
                    val photo = MediaStore.Images.Media.getBitmap(this.contentResolver, imageUri)
                    imageBase64 = convertBitmapToBase64(photo)
                }
            }
        }
    }

    private fun convertBitmapToBase64(bitmap: Bitmap): String {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream) // Cambia a JPEG
        val byteArray = outputStream.toByteArray()
        val base64String = Base64.encodeToString(byteArray, Base64.DEFAULT)
        return "data:image/jpeg;base64,$base64String"
    }


    private fun registrarUsuario(email: String, password: String, nombre: String, disponible: String, imagen: String) {
        coroutineScope.launch {
            val response = withContext(Dispatchers.IO) {
                ApiClient.instance.registrarUsuario(InicioSesionRequest(email, password, nombre, disponible, imagen))
            }

            if (response.isSuccessful && response.body()?.result == "ok") {
                Toast.makeText(this@InicioSesionActivity, "Registro exitoso. Por favor, inicie sesión.", Toast.LENGTH_LONG).show()
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

    private fun hasAllPermissionsGranted(): Boolean {
        for (permission in REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    private fun requestNecessaryPermissions() {
        ActivityCompat.requestPermissions(
            this,
            REQUIRED_PERMISSIONS,
            PERMISSIONS_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSIONS_REQUEST_CODE) {
            if (!hasAllPermissionsGranted()) {
                Toast.makeText(this, "Permisos necesarios no concedidos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
    }
}
