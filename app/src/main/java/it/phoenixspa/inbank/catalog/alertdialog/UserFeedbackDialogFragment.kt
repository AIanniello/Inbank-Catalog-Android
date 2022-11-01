package it.phoenixspa.inbank.catalog.alertdialog

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.InputFilter
import android.view.LayoutInflater
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import it.phoenixspa.inbank.catalog.R
import it.phoenixspa.inbank.theming.databinding.DialogEdittextMultilineBinding


class UserFeedbackDialogFragment : DialogFragment() {
    private lateinit var binding: DialogEdittextMultilineBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogEdittextMultilineBinding.inflate(LayoutInflater.from(context))
        val editText = binding.dialogTextinputedittext
        val maxChars = 100

        editText.filters = arrayOf<InputFilter>(
            InputFilter.LengthFilter(maxChars)
        )
        val dialogClickListener =
            DialogInterface.OnClickListener { dialog, which ->
                val message = editText.text.toString()
                when (which) {
                    DialogInterface.BUTTON_POSITIVE -> Toast.makeText(
                        requireContext(),
                        "Positive",
                        Toast.LENGTH_SHORT
                    ).show()
                    DialogInterface.BUTTON_NEGATIVE -> Toast.makeText(
                        requireContext(),
                        "Negative",
                        Toast.LENGTH_SHORT
                    ).show()
                    else -> {
                    }
                }
            }
        val builder = MaterialAlertDialogBuilder(requireContext())
        editText.setHint(R.string.crashReport_descHint)
        return builder.setMessage(R.string.crashReport_desc1)
            .setPositiveButton("Sì", dialogClickListener)
            .setNegativeButton("No", dialogClickListener)
            .setView(binding.root)
            //.setTitle("Title") // test title
            .create()
    }

}
