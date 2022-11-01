package it.phoenixspa.inbank.theming.utilities

import android.content.Context
import android.media.AudioManager
import android.text.Selection
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import it.phoenixspa.inbank.theming.R
import it.phoenixspa.inbank.theming.utilities.OnBackPressedKeyboardInterface


interface GetActivity {
    val activity: FragmentActivity
}

class ScrambledKeyboardV2(
    fragment: Fragment,
    currentEditText: EditText,
    buttons: List<Button>,
    button: View,
    val keyboardViewParent: View,
    rootView: View,
    private val getActivity: GetActivity
) : OnBackPressedKeyboardInterface {

    private fun EditText.setCursorEndPosition() {
        isCursorVisible = true
        Selection.setSelection(text, length())
    }

    private fun EditText.addChar(str: String) {
        if (str[0] != ' ') {
            text = text.append(str)
        }
        setCursorEndPosition()
    }

    private fun playClick() {
        getActivity.activity.also {
            val am = it.getSystemService(Context.AUDIO_SERVICE) as AudioManager
            val vol = 0.5f // This will be half of the default system sound
            am.playSoundEffect(AudioManager.FX_KEYPRESS_STANDARD, vol)
        }
    }

    private val btnListener = View.OnClickListener { v ->
        currentEditText.also {
            it.addChar(v.tag.toString())
            if (it.text.length <= 5) playClick()
        }
    }

    init {
        val ctx: Context = rootView.context
        ViewCompat.setElevation(keyboardViewParent, ctx.resources.getDimension(R.dimen.scrambled_elevation))

        if (fragment !is OnBackPressedKeyboardInterface){
            Log.w("ScrambledKeyboard", "The current fragment does not extend OnBackPressedKeyboardInterface, are you sure?")
            // throw Exception("Implement OnBackPressedKeyboardInterface on fragment and call ScrambledKeyboard3.onBackPressedKeyboard()")
        }

        if (!rootView.isFocusableInTouchMode) {
            throw Exception("Declare focusableInTouchMode inside the rootLayout")
        }

        val letters = "0123456789     "
        // indispensabile per nascondere la tastiera standard
        currentEditText.showSoftInputOnFocus = false
        currentEditText.setOnFocusChangeListener { _, focused ->
            keyboardViewParent.isVisible = focused
            onBackPressedCallback.isEnabled = focused
        }
        currentEditText.setOnClickListener {
            if (!keyboardViewParent.isVisible) {
                keyboardViewParent.isVisible = true
            }
            onBackPressedCallback.isEnabled = true
        }
        val arrayTmp = Utils.generaNumeriCasualiDistinti(15, false)
        for (i in 0..14) {
            val strTmp = "${letters[arrayTmp[i]]}"
            buttons[i].also { button ->
                button.tag = strTmp
                button.text = strTmp
                if (strTmp != " ") {
                    button.setOnClickListener(btnListener)
                }
            }

        }
        button.setOnClickListener {
            currentEditText.also {
                if (it.text.isNotEmpty()) {
                    var strTmp = it.text.toString()
                    strTmp = strTmp.substring(0, strTmp.length - 1)
                    it.setText(strTmp)
                    it.setCursorEndPosition()
                    playClick()
                }
            }
        }

        rootView.setOnClickListener {
            keyboardViewParent.isVisible = false
            onBackPressedCallback.isEnabled = false
        }
    }

    override fun onBackPressedKeyboard(): Boolean {
        if (keyboardViewParent.isVisible) {
            keyboardViewParent.isVisible = false
            onBackPressedCallback.isEnabled = false
            return true
        }
        return false
    }

    val onBackPressedCallback = object: OnBackPressedCallback(false) {
        override fun handleOnBackPressed() {
            onBackPressedKeyboard()
        }
    }
}