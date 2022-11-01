package it.phoenixspa.inbank.catalog.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import it.phoenixspa.inbank.catalog.R


open class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setSupportActionBar(findViewById(R.id.toolbar))

        if (savedInstanceState == null) {

            val navController = findNavController(R.id.nav_host_fragment)

            //navController.navigate(R.id.go_from_root_to_01)

            supportFragmentManager.addOnBackStackChangedListener {
                setupActionBarWithNavController(navController)
            }
        }
    }
}