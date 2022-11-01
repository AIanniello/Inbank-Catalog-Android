package it.phoenixspa.inbank.catalog.alertdialog

import android.content.DialogInterface
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import it.phoenixspa.inbank.catalog.R

class AlertDialogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alert_dialog)

        findViewById<Button>(R.id.button1).apply {
            setOnClickListener {
                showMaterialAlertDialog()
            }
        }
        findViewById<Button>(R.id.button2).apply {
            setOnClickListener {
                showAlertDialogFeedback()
            }
        }
        findViewById<Button>(R.id.button3).apply {
            setOnClickListener {
                showAlertDialog()
            }
        }
    }

    private fun showMaterialAlertDialog() {
        val alertDialog = MaterialAlertDialogBuilder(this@AlertDialogActivity)
            .setTitle("Titolo del dialog")
            .setMessage("Messaggio del dialog. Può occupare una sola riga o anche più di una a seconda del messaggio!")
            .setPositiveButton("Azione positiva") { dialog: DialogInterface, _: Int -> dialog.dismiss() }
            .setNegativeButton("Azione negativa") { dialog: DialogInterface, _: Int -> dialog.dismiss() }
            .create()
        alertDialog.show()
    }

    private fun showAlertDialog() {
        val builder = AlertDialog.Builder(this)

        val ad: AlertDialog = builder.setMessage(
            "Tutti gli AlertDialog.Builder che utilizzano lo stile Material," +
                    " devono essere sostituiti con MaterialAlertDialog. Altrimenti appaiono come questo."
        )
            .setPositiveButton("Sì", { dialogInterface, i ->

            })
//            .setNegativeButton("Sì", { dialogInterface, i ->
//
//            })
            .setTitle("Attenzione")
            .create()
        //serve per fare il multiline
        ad.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        ad.show()
    }

    private fun showAlertDialogFeedback() {
        UserFeedbackDialogFragment()
            .show(supportFragmentManager, TAG_CRASH_REPORT_DIALOG)
    }

    override fun onResume() {
        super.onResume()
//        showAlertDialog()
//        showMaterialAlertDialog()
    }

    companion object {
        private const val TAG_CRASH_REPORT_DIALOG = "TAG_CRASH_REPORT_DIALOG"
    }
}