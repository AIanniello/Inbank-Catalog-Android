package it.phoenixspa.inbank.theming.auth.password2

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import it.phoenixspa.inbank.theming.databinding.CheckboxStyled1Binding
import java.util.*

class CheckBoxStyled1(context: Context, attrs: AttributeSet? = null) : ConstraintLayout(context, attrs) {

    private lateinit var binding: CheckboxStyled1Binding

    init {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        binding = CheckboxStyled1Binding.inflate(LayoutInflater.from(context), this, true)
        fun Int.getCharSequence(): CharSequence? {
            return context.obtainStyledAttributes(attrs, intArrayOf(this)).let { typedArray ->
                val charSequenceFound = typedArray.getText(0)
                typedArray.recycle()
                charSequenceFound
            }
        }
        fun Int.getBool(): Boolean {
            return context.obtainStyledAttributes(attrs, intArrayOf(this)).let { typedArray ->
                val boolFound = typedArray.getBoolean(0, false)
                typedArray.recycle()
                boolFound
            }
        }

        text = android.R.attr.text.getCharSequence()
        isChecked = android.R.attr.checked.getBool()
    }

    var text: CharSequence? = null
        get() = field
        set(value) {
            field = value?.let{ textFound ->
                if(textFound.isEmpty()) {
                    ""
                } else {
                    textFound.substring(0, 1).toUpperCase(Locale.getDefault()) + textFound.substring(1, textFound.length)
                }
            } ?: ""
            updateTitle()
        }

    var isChecked: Boolean = false
        get() = field
        set(value) {
            field = value
            updateChecked()
        }

    private fun updateTitle() {
        binding.root.text = text
    }

    private fun updateChecked() {
        binding.root.isChecked = isChecked
    }

}
