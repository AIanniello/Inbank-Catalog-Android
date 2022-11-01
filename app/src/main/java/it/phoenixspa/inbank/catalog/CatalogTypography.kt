package it.phoenixspa.inbank.catalog

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButtonToggleGroup
import java.lang.IndexOutOfBoundsException

class CatalogTypography : AppCompatActivity() {

    private var mode = TextMode.NORMAL

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalog_typography)
        setSupportActionBar(findViewById(R.id.toolbar))

        val text = findViewById<TextView>(R.id.text)
        val kindOfChar = findViewById<TextView>(R.id.kindOfChar)
        val title = findViewById<TextView>(R.id.title)
        val seekBar = findViewById<SeekBar>(R.id.seekBar)

        fun Int.changeStyle(mode: TextMode) {
            this@CatalogTypography.findViewById<Button>(this).let {
                it.setOnClickListener {
                    this@CatalogTypography.mode = mode
                    text.setStyleTextColor(mode)
                    text.textSize = getDimensionDPByInt(seekBar.progress)
                }
            }
        }


        findViewById<MaterialButtonToggleGroup>(R.id.buttonToggleGroup).let {
            it.addOnButtonCheckedListener(object :
                MaterialButtonToggleGroup.OnButtonCheckedListener {
                override fun onButtonChecked(
                    group: MaterialButtonToggleGroup?,
                    checkedId: Int,
                    isChecked: Boolean
                ) {
                    if (!isChecked) {
                        return
                    }
                    when (checkedId) {
                        R.id.button11 -> checkedId.changeStyle(TextMode.NORMAL)
                        R.id.button22 -> checkedId.changeStyle(TextMode.POSITIVE)
                        R.id.button33 -> checkedId.changeStyle(TextMode.NEGATIVE)
                        R.id.button44 -> checkedId.changeStyle(TextMode.INVERSE)
                    }
                }

            })
        }

        seekBar.let {
            it.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

                override fun onProgressChanged(seekBar: SeekBar?, value: Int, fromUser: Boolean) {
                    text.setTextAppearance(getStyleByInt(value))
                    text.textSize = getDimensionDPByInt(value)
                    text.setStyleTextColor(this@CatalogTypography.mode)
                    title.text = getDescriptionByInt(value)
                    kindOfChar.text = getDescription2ByInt(value)
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {

                }

                override fun onStopTrackingTouch(p0: SeekBar?) {

                }

            })
        }
    }

    override fun onStart() {
        super.onStart()
        supportActionBar?.title = getString(R.string.typography_ab_title)
    }

    enum class TextMode {
        NORMAL, POSITIVE, NEGATIVE, INVERSE
    }

    private fun TextView.setStyleTextColor(value: TextMode) {
        val textColorRes = when (value) {
            TextMode.NORMAL -> R.color.grigio_1
            TextMode.POSITIVE -> R.color.positive
            TextMode.NEGATIVE -> R.color.negative
            TextMode.INVERSE -> R.color.reverse
        }
        val backgroundColorRes = when (value) {
            TextMode.NORMAL,
            TextMode.POSITIVE,
            TextMode.NEGATIVE -> R.color.reverse
            TextMode.INVERSE -> R.color.brandcolor_1
        }
        setTextColor(
            ContextCompat.getColor(
                this@CatalogTypography,
                textColorRes
            )
        )
        setBackgroundColor(
            ContextCompat.getColor(
                this@CatalogTypography,
                backgroundColorRes
            )
        )

    }


    private fun Int.getDimensionDP(): Float {
        val resources = this@CatalogTypography.resources
        return (resources.getDimension(this) / resources.getDisplayMetrics().density)
    }

    private fun getStyleByInt(size: Int): Int {
        return when (size) {
            0 -> R.style.S_Regular
            1 -> R.style.S_Bold
            2 -> R.style.M_Regular
            3 -> R.style.M_Bold
            4 -> R.style.L_Regular
            5 -> R.style.L_Medium
            6 -> R.style.L_Bold
            7 -> R.style.XL_Regular
            8 -> R.style.XL_Medium
            9 -> R.style.XL_Bold
            else -> throw IndexOutOfBoundsException()
        }
    }

    /**
     * @param size
     * 0 = S
     * 1 = S
     * 2 = M
     * 3 = M
     * 4 = L
     * 5 = L
     * 6 = L
     * 7 = XL
     * 8 = XL
     * 9 = XL
     */
    private fun getDimensionDPByInt(size: Int): Float {
        return when (size) {
            0, 1 -> R.dimen.text_small.getDimensionDP()
            2, 3 -> R.dimen.text_medium.getDimensionDP()
            4, 5, 6 -> R.dimen.text_large.getDimensionDP()
            7, 8, 9 -> R.dimen.text_xlarge.getDimensionDP()
            else -> throw IndexOutOfBoundsException()
        }
    }

    /**
     * @param size
     * 0 = S Regular
     * 1 = S Regular Bold
     * 2 = M Regular
     * 3 = M Regular Bold
     * 4 = L Regular
     * 5 = L Medium
     * 6 = L Regular Bold
     * 7 = XL Regular
     * 8 = XL Medium
     * 9 = XL Regular Bold
     */
    private fun getDescriptionByInt(size: Int): String {
        return when (size) {
            0, 1 -> getString(R.string.typography_dimension, getString(R.string.typography_s))
            2, 3 -> getString(R.string.typography_dimension, getString(R.string.typography_m))
            4, 5, 6 -> getString(R.string.typography_dimension, getString(R.string.typography_l))
            7, 8, 9 -> getString(R.string.typography_dimension, getString(R.string.typography_xl))
            else -> throw IndexOutOfBoundsException()
        }
    }

    /**
     * @param size
     * 0 = S Regular
     * 1 = S Regular Bold
     * 2 = M Regular
     * 3 = M Regular Bold
     * 4 = L Regular
     * 5 = L Medium
     * 6 = L Regular Bold
     * 7 = XL Regular
     * 8 = XL Medium
     * 9 = XL Regular Bold
     */
    private fun getDescription2ByInt(size: Int): String {
        return when (size) {
            0, 2, 4, 7 -> getString(R.string.typography_regular)
            5, 8 -> getString(R.string.typography_medium)
            1, 3, 6, 9 -> getString(R.string.typography_regular_bold)
            else -> throw IndexOutOfBoundsException()
        }
    }

}