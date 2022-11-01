package it.phoenixspa.inbank.catalog.firstlogin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import it.phoenixspa.inbank.catalog.R
import it.phoenixspa.inbank.catalog.activities.DimensionActivity


open class FirstLoginActivity : DimensionActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_login)
        setSupportActionBar(findViewById(R.id.toolbar))

        if (savedInstanceState == null) {



            // l'utente ha già attivato un profilo sull'app?
            val firstLogin = true

            val navController = findNavController(R.id.nav_host_fragment)

            navController.navigate(if (firstLogin) {
                R.id.go_from_root_to_01
            } else{
                R.id.go_from_root_to_02
            })

            supportFragmentManager.addOnBackStackChangedListener {
                setupActionBarWithNavController(navController)
            }
        }
    }
}