package it.phoenixspa.inbank.theming

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import it.phoenixspa.inbank.theming.databinding.KeyboardScrambledMaterialBinding

/**
 */
class ScrambledKeyboard(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    lateinit var binding: KeyboardScrambledMaterialBinding


    private fun init(context: Context, attrs: AttributeSet) {
        binding = KeyboardScrambledMaterialBinding.inflate(LayoutInflater.from(context), this, true)

    }

    init {
        init(context, attrs)
    }
}
