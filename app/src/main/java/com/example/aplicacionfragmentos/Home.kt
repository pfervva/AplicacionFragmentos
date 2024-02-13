package com.example.aplicacionfragmentos

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.aplicacionfragmentos.databinding.ActivityHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class Home : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarHome.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navController = findNavController(R.id.nav_host_fragment_content_home)
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow), drawerLayout
        )

        // Configuración del BottomNavigationView
        val bottomNavView: BottomNavigationView = binding.bottomNavigation
        bottomNavView.setupWithNavController(navController)

        // Configuración automática de la navegación para el navView lateral
        val sideNavView: NavigationView = binding.navView
        setupActionBarWithNavController(navController, appBarConfiguration)
        sideNavView.setupWithNavController(navController)

        // Configurar el listener de NavigationView para manejar el clic en "Salir" manteniendo la navegación automática
        sideNavView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_logout -> {
                    logout()
                    true // Indica que has manejado el evento
                }
                else -> {
                    // Permite que la navegación automática maneje otros elementos del menú
                    menuItem.onNavDestinationSelected(navController) || super.onOptionsItemSelected(menuItem)
                }
            }
        }
    }

    private fun logout() {
        // Limpia las preferencias compartidas para cerrar sesión
        val preferences = getSharedPreferences(RegistroActivity.PREFS_NAME, MODE_PRIVATE)
        preferences.edit().apply {
            clear()
            apply()
        }
        // Redirige al usuario a la pantalla de inicio de sesión
        val intent = Intent(this, InicioSesionActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_home)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
