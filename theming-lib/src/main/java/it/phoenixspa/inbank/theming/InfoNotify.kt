package it.phoenixspa.inbank.theming

import android.content.Context
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout


class InfoNotify(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    lateinit var labelTextView: TextView
    lateinit var imageView: ImageView

    private fun init(context: Context, attrs: AttributeSet) {
        inflate(context, R.layout.info_notify, this)

        fun Int.getCharSequence(): CharSequence? {
            return context.obtainStyledAttributes(attrs, intArrayOf(this)).let { typedArray ->
                val charSequenceFound = typedArray.getText(0)
                typedArray.recycle()
                charSequenceFound
            }
        }

        fun Int.getDrawableRes(): Int? {
            return context.obtainStyledAttributes(attrs, intArrayOf(this)).let { typedArray ->
                val drawableRes = typedArray.getResourceId(0, 0)
                typedArray.recycle()
                if (drawableRes == 0) null else drawableRes
            }
        }

        val firstPartLabel = R.attr.textLabelFirstPart.getCharSequence() ?: ""
        val secondPartLabel = R.attr.textLabelSecondPart.getCharSequence() ?: ""
        val image = R.attr.src.getDrawableRes()

        initComponents()


        val textToApply = SpannableStringBuilder()
        textToApply.append("$firstPartLabel ")
        val start = textToApply.length
        textToApply.append(secondPartLabel)
        val secondPartBold = false
        if (secondPartBold) {
            textToApply.setSpan(
                StyleSpan(Typeface.BOLD),
                start,
                textToApply.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }

        labelTextView.text = textToApply

        image?.let {
            imageView.visibility = View.VISIBLE
            imageView.setImageResource(image)
        } ?: let {
            imageView.visibility = View.GONE
        }
    }

    private fun initComponents() {
        labelTextView = findViewById(R.id.label)
        imageView = findViewById(R.id.imageView)
    }

    fun getLabelText(): CharSequence {
        return labelTextView.text
    }

    fun setLabelText(value: CharSequence?) {
        labelTextView.text = value
    }


    init {
        init(context, attrs)
    }
}