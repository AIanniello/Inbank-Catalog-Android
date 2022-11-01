package it.phoenixspa.inbank.theming

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.google.android.material.switchmaterial.SwitchMaterial
import it.phoenixspa.inbank.theming.databinding.LayoutTextWithSwitchBinding

/**
 * Può essere impostata tramite xml utilizzando gli attributi:
 * textTitle, textSubtitle, textStatus, showSwitch
 *
 * Il comportamento di default è visualizzare lo switch;
 * può essere nascosto e sostituito dalla TextView status
 *
 * È possibile cambiare la visibilità tramite [showSwitch]
 *
 * Il layout di riferimento è [LayoutTextWithSwitchBinding]
 */
class TextWithSwitch(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private lateinit var binding: LayoutTextWithSwitchBinding

    private fun init(context: Context, attrs: AttributeSet) {
        binding = LayoutTextWithSwitchBinding.inflate(LayoutInflater.from(context), this, true)

        fun Int.getCharSequence(): CharSequence? {
            return context.obtainStyledAttributes(attrs, intArrayOf(this)).let { typedArray ->
                val charSequenceFound = typedArray.getText(0)
                typedArray.recycle()
                charSequenceFound
            }
        }

        fun getShowSwitch(): Boolean {
            return context.obtainStyledAttributes(attrs, R.styleable.TextWithSwitch)
                .let { typedArray ->
                    val boolean =
                        typedArray.getBoolean(R.styleable.TextWithSwitch_showSwitch, true)
                    typedArray.recycle()
                    boolean
                }
        }

        val title = R.attr.textTitle.getCharSequence()
        val subtitle = R.attr.textSubtitle.getCharSequence()
        val status = R.attr.textStatus.getCharSequence()

        showSwitch = getShowSwitch()

        title?.let {
            binding.title.text = it
        }
        subtitle?.let {
            binding.subtitle.text = it
        }
        status?.let {
            binding.status.text = it
        }
    }

    var title: CharSequence? = null
        get() = field
        set(value) {
            field = value
            updateTitle()
        }

    var subtitle: CharSequence? = null
        get() = field
        set(value) {
            field = value
            updateSubtitle()
        }

    var status: CharSequence? = null
        get() = field
        set(value) {
            field = value
            updateStatus()
        }

    private fun updateTitle() {
        binding.title.text = title
    }

    private fun updateSubtitle() {
        binding.subtitle.text = subtitle
    }

    private fun updateStatus() {
        binding.status.text = status
    }

    fun getSwitch(): SwitchMaterial = binding.switch1

    var showSwitch: Boolean = false
        get() = field
        set(value) {
            field = value
            updateVisibility()
        }

    private fun updateVisibility() {
        binding.switch1.isVisible = showSwitch
        binding.status.isVisible = !showSwitch
    }

    init {
        init(context, attrs)
    }
}