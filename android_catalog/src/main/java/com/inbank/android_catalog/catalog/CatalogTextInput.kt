package com.inbank.android_catalog.catalog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.inbank.android_catalog.R

class CatalogTextInput : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalog_text_input)
        setSupportActionBar(findViewById(R.id.toolbar))
    }

    override fun onStart() {
        super.onStart()
        supportActionBar?.title = getString(R.string.text_input_ab_title)
    }
}