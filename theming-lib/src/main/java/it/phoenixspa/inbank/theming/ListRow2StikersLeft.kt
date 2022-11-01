package it.phoenixspa.inbank.theming

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.ColorRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import it.phoenixspa.inbank.theming.databinding.ListRow2StikersLeftBinding
import java.util.*

/**
 * Classe che semplifica la rappresentazione grafica dell'utenza
 *
 * Può essere impostata tramite xml utilizzando gli attributi:
 * textTitle, textSubtitle, hideSubtitle, circleColor, android:src (immagine drawable)
 *
 * Il comportamento di default dell'immagine a sinistra è quello di utilizzare il
 *  cerchio [R.color.circle_background2] con il colore [R.color.circle_background2]
 *  Inoltre sopra l'immagine sarà applicata la lettera iniziale del valore di [title]
 *
 * È possibile impostare l'immagine tramite [setCircleImageBitmap] passando una bitmap oppure tramite
 *  [circleImageRes] passando il riferimento al drawable
 *
 * Tramite [hideSubtitle] è possibile offuscare il sottotitolo tramite [CharObfuscated]
 *
 * Il layout di riferimento è [ListRow2StikersLeftBinding]
 */
class ListRow2StikersLeft(context: Context, attrs: AttributeSet) :
    ConstraintLayout(context, attrs) {

    private val CharObfuscated = "•"
    private val nrCharObfuscatedVisualized = 8

    private lateinit var binding: ListRow2StikersLeftBinding

    private var hasImageBitmap = false

    init {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet) {
        binding = ListRow2StikersLeftBinding.inflate(LayoutInflater.from(context), this, true)

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

        fun getCircleColor(): Int {
            return context.obtainStyledAttributes(attrs, R.styleable.ListRow2StikersLeft)
                .let { typedArray ->
                    val colorResId = typedArray.getResourceId(
                        R.styleable.ListRow2StikersLeft_circleColor,
                        R.color.circle_background2
                    )
                    typedArray.recycle()
                    colorResId
                }
        }

        fun getHideChars(): Boolean {
            return context.obtainStyledAttributes(attrs, R.styleable.ListRow2StikersLeft)
                .let { typedArray ->
                    val boolean =
                        typedArray.getBoolean(R.styleable.ListRow2StikersLeft_hideSubtitle, false)
                    typedArray.recycle()
                    boolean
                }
        }

        val title = R.attr.textTitle.getCharSequence()
        val subtitle = R.attr.textSubtitle.getCharSequence()
        @androidx.annotation.DrawableRes
        val imageRes: Int? = android.R.attr.src.getDrawableRes()
        val circleColorRes = getCircleColor()
        val hideSubtitle = getHideChars()

        this.title = title
        this.subtitle = subtitle
        this.circleImageRes = imageRes
        this.circleColorRes = circleColorRes
        this.hideSubtitle = hideSubtitle
    }

    var title: CharSequence? = null
        get() = field
        set(value) {
            field = value
            value?.let {
                binding.title.text = it
                binding.image.setTextInside(it.substring(0, 1).toUpperCase(Locale.getDefault()))
                binding.image.contentDescription =
                    context.getString(R.string.il01a_image_content_description, it)
            } ?: let {
                binding.image.setTextInside("")
                binding.image.contentDescription =
                    context.getString(R.string.il01a_image_content_description, "")
            }
        }

    var subtitle: CharSequence? = null
        get() = field
        set(value) {
            field = value
            updateSubtitle()
        }

    /**
     * Aggiorna il testo del sottotitolo obfuscandolo in base a [hideSubtitle]
     * Utilizzato il carattere [CharObfuscated] impostata lunghezza fissa di [nrCharObfuscatedVisualized]
     */
    private fun updateSubtitle() {
        binding.subtitle.text = if (hideSubtitle) CharObfuscated.repeat(nrCharObfuscatedVisualized) else subtitle
    }

    fun setCircleImageBitmap(bitmap: Bitmap?) {
        bitmap?.let {
            binding.image.setImageBitmap(bitmap)
            hasImageBitmap = true
        } ?: let {
            hasImageBitmap = false
            binding.image.setImageResource(R.drawable.circle_background_40dp)
        }
        updateCircleColorRes()
    }

    @androidx.annotation.DrawableRes
    var circleImageRes: Int? = null
        get() = field
        set(value) {
            field = value
            circleImageRes?.let {
                binding.image.setImageResource(it)
                hasImageBitmap = false
            } ?: let {
                hasImageBitmap = false
            }
            updateCircleColorRes()
        }

    /**
     * Imposta il colore di sfondo del cerchio
     */
    @ColorRes
    var circleColorRes: Int? = null
        get() = field
        set(value) {
            field = value
            updateCircleColorRes()
        }

    /**
     * il colore del cerchio viene applicato solamente se
     * 1 non esiste una risorsa drawable impostata [circleImageRes]
     * 2 non esiste una bitmap impostata ![hasImageBitmap]
     * 3 esiste [circleColorRes] impostato
     */
    private fun updateCircleColorRes() {
        if (circleColorRes != null && circleColorRes != 0
            && (circleImageRes == null || circleImageRes == 0)
            && !hasImageBitmap
        ) {
            circleColorRes?.let { color ->
                binding.image.setColorCircle(color);
            }
        } else {
            binding.image.setColorCircle(android.R.color.transparent);
        }
    }

    var hideSubtitle: Boolean = false
        get() = field
        set(value) {
            field = value
            updateSubtitle()
        }

    /**
     * Utilizzata nel progetto App
     */
    var pictureProfileView: ProfilePictureView = this.binding.image

}