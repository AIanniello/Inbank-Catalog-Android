package it.phoenixspa.inbank.theming

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import it.phoenixspa.inbank.theming.databinding.TitleMBinding

/**
 * Può essere impostato tramite xml utilizzando l'attributo textTitle
 *
 * Il layout di riferimento è [TitleMBinding]
 */
class TitleM(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private lateinit var binding: TitleMBinding

    private fun init(context: Context, attrs: AttributeSet) {
        binding = TitleMBinding.inflate(LayoutInflater.from(context), this, true)

        fun Int.getCharSequence(): CharSequence? {
            return context.obtainStyledAttributes(attrs, intArrayOf(this)).let { typedArray ->
                val charSequenceFound = typedArray.getText(0)
                typedArray.recycle()
                charSequenceFound
            }
        }

        title = R.attr.textTitle.getCharSequence()
    }

    var title: CharSequence? = null
        get() = field
        set(value) {
            field = value
            updateTitle()
        }

    private fun updateTitle() {
        binding.title.text = title
    }

    init {
        init(context, attrs)
    }
}