package com.example.aplicacionfragmentos

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.aplicacionfragmentos.databinding.ActivityHomeBinding
import com.example.aplicacionfragmentos.ui.Home.HomeViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class Home : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()

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

        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)

        // Configuración del BottomNavigationView
        val bottomNavView: BottomNavigationView = binding.bottomNavigation
        bottomNavView.setupWithNavController(navController)

        // Configuración automática de la navegación para el NavigationView lateral
        val sideNavView: NavigationView = binding.navView
        sideNavView.setupWithNavController(navController)

        // Configurar el listener de NavigationView para manejar el clic en "Salir"
        sideNavView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_logout -> {
                    homeViewModel.logout()
                    navigateToLogin()
                    true
                }
                else -> menuItem.onNavDestinationSelected(navController) || super.onOptionsItemSelected(menuItem)
            }
        }
    }

    private fun navigateToLogin() {
        startActivity(Intent(this, InicioSesionActivity::class.java))
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_home)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
