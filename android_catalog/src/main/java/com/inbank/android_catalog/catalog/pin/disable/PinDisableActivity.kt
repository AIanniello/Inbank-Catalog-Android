package com.inbank.android_catalog.catalog.pin.disable

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.inbank.android_catalog.R
import com.inbank.android_catalog.catalog.activities.DimensionActivity

class PinDisableActivity : DimensionActivity(), PinDisableFragment.PinDisableListener {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disable_pin)

        navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(R.id.disable_pin_02),
            fallbackOnNavigateUpListener = ::onSupportNavigateUp
        )
        findViewById<Toolbar>(R.id.toolbar).setupWithNavController(navController, appBarConfiguration)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onDisablePinSuccess() {
        navController.navigate(R.id.action_disable_pin_01_to_disable_pin_02)
    }

    override fun onDisableCompleteButtonEndPressed() {
        finish()
    }

}

