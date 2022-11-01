package it.phoenixspa.inbank.catalog

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import it.phoenixspa.inbank.catalog.R

class CatalogButtonsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalog_buttons)
        setSupportActionBar(findViewById(R.id.toolbar))
    }

    override fun onStart() {
        super.onStart()
        supportActionBar?.title = getString(R.string.catalog_buttons_ab_title)
    }
}