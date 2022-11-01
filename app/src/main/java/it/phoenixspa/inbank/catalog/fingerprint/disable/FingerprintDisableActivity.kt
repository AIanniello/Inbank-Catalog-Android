package it.phoenixspa.inbank.catalog.fingerprint.disable

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import it.phoenixspa.inbank.catalog.R

class FingerprintDisableActivity : AppCompatActivity(),
    FingerprintDisableStartFragment.OnFingerprintDisabledListener {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fingerprint_disable)

        navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(R.id.disable_fingerprint_02),
            fallbackOnNavigateUpListener = ::onSupportNavigateUp
        )
        findViewById<Toolbar>(R.id.toolbar)
                .setupWithNavController(navController, appBarConfiguration)

    }

    override fun onSupportNavigateUp(): Boolean {
        if (!super.onSupportNavigateUp()) {
            finish()
        }
        return true
    }

    override fun onFingerprintDisableClick() {
        navController.navigate(R.id.action_disable_fingerprint_01_to_disable_fingerprint_02)
    }

    override fun onFingerprintDisabledSuccessful() {
        finish()
    }

}