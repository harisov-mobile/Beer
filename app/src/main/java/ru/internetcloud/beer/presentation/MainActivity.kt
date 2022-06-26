package ru.internetcloud.beer.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.internetcloud.beer.R

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupNavigation()
    }

    private fun setupNavigation() {

        // т.к. в activity_main.xml используем тег FragmentContainerView, приходится navController доставать
        // длинным путем:
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment

        navController = navHostFragment.navController

        setupActionBarWithNavController(navController)

        // нижнее меню:
        val bottomNavBar = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        bottomNavBar.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        // этот метод отвечает за "стрелочку" в верхнем тул-баре
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
