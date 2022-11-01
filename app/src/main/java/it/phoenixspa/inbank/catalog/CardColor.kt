package it.phoenixspa.inbank.catalog

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat

class CardColor(context: Context, attrs: AttributeSet): ConstraintLayout(context, attrs) {
    lateinit var view: View
    lateinit var titleTextView: TextView
    lateinit var subtitleTextView: TextView


    private fun init(context: Context, attrs: AttributeSet) {
        inflate(context, R.layout.color_card, this)

        fun Int.getCharSequence(): CharSequence?{
            return context.obtainStyledAttributes(attrs, intArrayOf(this)).let{ typedArray ->
                val charSequenceFound = typedArray.getText(0)
                typedArray.recycle()
                charSequenceFound
            }
        }
        fun Int.getColorSrc(): Int{
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.cardColor)
            val resColor = typedArray.getResourceId(R.styleable.cardColor_colorSquare, android.R.color.transparent)
            val color = ContextCompat.getColor(context, resColor)
            typedArray.recycle()
            return color
        }

        val title = R.attr.textTitle.getCharSequence()
        val subtitle = R.attr.textSubtitle.getCharSequence()
        val colorRes = R.attr.colorSquare.getColorSrc()

        initComponents()
        view.setBackgroundColor(colorRes)
        titleTextView.setText(title)
        subtitleTextView.setText(subtitle)
    }

    private fun initComponents() {
        view = findViewById(R.id.view)
        titleTextView = findViewById(R.id.title)
        subtitleTextView = findViewById(R.id.subtitle)
    }



    init {
        init(context, attrs)
    }
}