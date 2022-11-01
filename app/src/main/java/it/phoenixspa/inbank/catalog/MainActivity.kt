package it.phoenixspa.inbank.catalog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import it.phoenixspa.inbank.catalog.alertdialog.AlertDialogActivity
import it.phoenixspa.inbank.catalog.fingerprint.disable.FingerprintDisableActivity
import it.phoenixspa.inbank.catalog.firstlogin.FirstLoginActivity
import it.phoenixspa.inbank.catalog.login.LoginActivity
import it.phoenixspa.inbank.catalog.pin.disable.PinDisableActivity
import it.phoenixspa.inbank.catalog.welcome.WelcomeActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button1).apply{
            setOnClickListener {
                startActivity(Intent(this@MainActivity, CatalogButtonsActivity::class.java))
            }
        }
        findViewById<Button>(R.id.button2).apply{
            setOnClickListener {
                startActivity(Intent(this@MainActivity, CatalogTextInput::class.java))
            }
        }
        findViewById<Button>(R.id.button3).apply{
            setOnClickListener {
                startActivity(Intent(this@MainActivity, TextPlusThemeActivity::class.java))
            }
        }
        findViewById<Button>(R.id.button4).apply{
            setOnClickListener {
                startActivity(Intent(this@MainActivity, CatalogTypography::class.java))
            }
        }
        findViewById<Button>(R.id.button5).apply{
            setOnClickListener {
                startActivity(Intent(this@MainActivity, CatalogPaletteActivity::class.java))
            }
        }
        findViewById<Button>(R.id.button6).apply{
            setOnClickListener {
                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            }
        }
        findViewById<Button>(R.id.button7).apply{
            setOnClickListener {
                startActivity(Intent(this@MainActivity, FirstLoginActivity::class.java))
            }
        }
        findViewById<Button>(R.id.button8).apply{
            setOnClickListener {
                startActivity(Intent(this@MainActivity, WelcomeActivity::class.java))
            }
        }
        findViewById<Button>(R.id.button9).apply{
            setOnClickListener {
                startActivity(Intent(this@MainActivity, AlertDialogActivity::class.java))
            }
        }
        findViewById<Button>(R.id.button10).apply{
            setOnClickListener {
                startActivity(Intent(this@MainActivity, PinDisableActivity::class.java))
            }
        }
        findViewById<Button>(R.id.button11).apply{
            setOnClickListener {
                startActivity(Intent(this@MainActivity, FingerprintDisableActivity::class.java))
            }
        }
    }
}