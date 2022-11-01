package it.phoenixspa.inbank.theming

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import kotlin.math.min


class ProfilePictureView : View {

    private var drawableSrc: Drawable? = null
    var showCircumference = true
    var ticknessCircumference: Float = 2f

    @ColorRes
    private var colorCircumferenceRes: Int = R.color.maskImageView_border

    @ColorRes
    private var colorCircleRes: Int = R.color.circle_background2

    @ColorRes
    private var colorTextInsideRes: Int = R.color.reverse

    private var textInside: String = ""

    private var sizeTextInside: Float = 18f

    constructor(context: Context) : super(context) {

    }

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) :
            super(context, attrs, defStyle) {
        fun Int.getDrawableRes(): Int? {
            return context.obtainStyledAttributes(attrs, intArrayOf(this)).let { typedArray ->
                val drawableRes = typedArray.getResourceId(0, 0)
                typedArray.recycle()
                if (drawableRes == 0) null else drawableRes
            }
        }

        fun getShowCircumference(): Boolean {
            return context.obtainStyledAttributes(attrs, R.styleable.PictureProfileView)
                .let { typedArray ->
                    val boolean =
                        typedArray.getBoolean(
                            R.styleable.PictureProfileView_showCircumference,
                            true
                        )
                    typedArray.recycle()
                    boolean
                }
        }

        fun getTicknessCircumference(): Float {
            return context.obtainStyledAttributes(attrs, R.styleable.PictureProfileView)
                .let { typedArray ->
                    val tickness =
                        typedArray.getDimension(
                            R.styleable.PictureProfileView_ticknessCircumference,
                            2f
                        )
                    typedArray.recycle()
                    tickness
                }
        }

        fun getCircumferenceColor(): Int {
            return context.obtainStyledAttributes(attrs, R.styleable.PictureProfileView)
                .let { typedArray ->
                    val colorResId = typedArray.getResourceId(
                        R.styleable.PictureProfileView_colorCircumference,
                        R.color.maskImageView_border
                    )
                    typedArray.recycle()
                    colorResId
                }
        }

        fun getCircleColor(): Int {
            return context.obtainStyledAttributes(attrs, R.styleable.PictureProfileView)
                .let { typedArray ->
                    val colorResId = typedArray.getResourceId(
                        R.styleable.PictureProfileView_colorCircle,
                        R.color.circle_background2
                    )
                    typedArray.recycle()
                    colorResId
                }
        }

        fun getTextInsideColor(): Int {
            return context.obtainStyledAttributes(attrs, R.styleable.PictureProfileView)
                .let { typedArray ->
                    val colorResId = typedArray.getResourceId(
                        R.styleable.PictureProfileView_colorTextInside,
                        R.color.reverse
                    )
                    typedArray.recycle()
                    colorResId
                }
        }

        fun getTextInside(): String {
            return context.obtainStyledAttributes(attrs, R.styleable.PictureProfileView)
                .let { typedArray ->
                    val str = typedArray.getString(
                        R.styleable.PictureProfileView_textInside
                    )
                    typedArray.recycle()
                    str ?: ""
                }
        }
        fun getSizeTextInside(): Float {
            return context.obtainStyledAttributes(attrs, R.styleable.PictureProfileView)
                .let { typedArray ->
                    val tickness =
                        typedArray.getDimension(
                            R.styleable.PictureProfileView_sizeTextInside,
                            18f
                        )
                    typedArray.recycle()
                    tickness
                }
        }

        val imageRes = android.R.attr.src.getDrawableRes()
        drawableSrc = imageRes?.let {
            ResourcesCompat.getDrawable(context.resources, it, null)
        }

        showCircumference = getShowCircumference()
        ticknessCircumference = getTicknessCircumference()
        colorCircumferenceRes = getCircumferenceColor()
        colorCircleRes = getCircleColor()
        colorTextInsideRes = getTextInsideColor()
        textInside = getTextInside().toUpperCase()
        sizeTextInside = getSizeTextInside()
        val a = 0
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (drawableSrc != null) {
            drawPhoto(canvas)
        } else {
            drawColorAndText(canvas)
        }
    }

    private fun drawColorAndText(canvas: Canvas) {
        val diameter: Int = Math.min(width, height)
        val radius = diameter / 2

        // È necessario il colore nero per fare il mask con l'immagine
        val mBackColor = ContextCompat.getColor(context, colorCircleRes)
        val myPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = mBackColor
            isFilterBitmap = false
            isAntiAlias = true
        }

        drawCircle(canvas, myPaint, radius)
        myPaint.apply {
            color = ContextCompat.getColor(context, colorTextInsideRes)
            textSize = sizeTextInside
        }
        val bounds = Rect()
        myPaint.setTextAlign(Paint.Align.CENTER);
        myPaint.getTextBounds(textInside, 0, textInside.length, bounds);
        val left = canvas.width / 2f
        val top = (diameter + bounds.height()) / 2f
        canvas.drawText(textInside, left, top, myPaint)
    }

    private fun drawPhoto(canvas: Canvas) {
        val diameter: Int = Math.min(width, height)
        val radius = diameter / 2

        // È necessario il colore nero per fare il mask con l'immagine
        val mBackColor = ContextCompat.getColor(context, android.R.color.black)

        val myPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = mBackColor
            isFilterBitmap = false
            isAntiAlias = true
        }

        val output = Bitmap.createBitmap(diameter, diameter, Bitmap.Config.ARGB_8888)
        // è necessario un secondo canvas per permettere di effettuare il mask delle immagini
        val canvasMask = Canvas(output)
        drawCircle(canvasMask, myPaint, radius)
        canvasMask.drawARGB(0, 0, 0, 0)

        drawableSrc?.let {
            drawImage(canvasMask, myPaint, it, diameter)

            myPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_OVER)
            if (showCircumference) {
                drawCircumference(canvasMask)
            }

        }
        canvas.drawBitmap(output, 0f, 0f, myPaint)
    }

    private fun drawImage(canvas: Canvas, paint: Paint, drawable: Drawable, sideSquare: Int) {
        val bitmap = drawable.toBitmap()
        val rectSrc = Rect(0, 0, bitmap.width, bitmap.height)

        var mLeft = 0
        var mTop = 0
        var newWidth = 0
        var newHeight = 0

        // adatto e taglio l'immagine in base a sideSquare (a seconda dell'orientamento)
        val rectDst = if (bitmap.width >= bitmap.height) {
            newHeight = sideSquare
            newWidth = bitmap.width * newHeight / bitmap.height
            mLeft = (sideSquare - newWidth) / 2 + paddingLeft
            mTop = (sideSquare - newHeight) / 2 + paddingTop
            Rect(mLeft, mTop, mLeft + newWidth - 0, mTop + newHeight)
        } else {
            newWidth = sideSquare
            newHeight = bitmap.height * newWidth / bitmap.width
            mLeft = (sideSquare - newWidth) / 2 + paddingLeft
            mTop = (sideSquare - newHeight) / 2 + paddingTop
            Rect(mLeft, mTop, mLeft + newWidth, mTop + newHeight)
        }

        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(bitmap, rectSrc, rectDst, paint)
    }

    private fun drawCircle(canvas: Canvas, paint: Paint, radius: Int) {
        canvas.drawCircle(
            radius.toFloat(),
            radius.toFloat(),
            radius.toFloat(),
            paint
        )
    }

    private fun drawCircumference(canvas: Canvas) {
        val min = min(width, height) / 2

        val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = ContextCompat.getColor(context, colorCircumferenceRes)
            style = Paint.Style.STROKE
            isAntiAlias = true
            strokeWidth = ticknessCircumference
        }
        canvas.drawCircle(
            (width / 2).toFloat(),
            (height / 2).toFloat(),
            (min - ticknessCircumference / 2),
            paint
        )
    }

    fun setImageBitmap(bitmap: Bitmap) {
        setImageDrawable(BitmapDrawable(resources, bitmap))
    }

    fun setImageResource(imageRes: Int) {
        ResourcesCompat.getDrawable(context.resources, imageRes, null)?.also { drawable ->
            setImageDrawable(drawable)
        }
    }

    fun setImageDrawable(drawable: Drawable) {
        drawableSrc = drawable
        invalidate()
    }

    fun setColorCircle(@ColorRes newColor: Int) {
        // il colore non può essere trasparente
        colorCircleRes = newColor
        invalidate()
    }

    fun setTextInside(value: String) {
        textInside = value.toUpperCase()
        invalidate()
    }

    companion object {

    }

}
