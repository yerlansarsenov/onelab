package kz.moviedb.presentation.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.cardview.widget.CardView

/**
 * Created by Sarsenov Yerlan on 27.01.2021.
 */

class CustomRatingView : CardView {
    private var paintRate = Paint()
    private var paintBg = Paint()
    private var paintText = Paint()
    var rate: Float = 60.0f
    private var rateWidth = (width * rate / 100.0).toInt()
    private var text = "${rate/10}/10.0"

    constructor(context: Context) : super(context){
        init()
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        init()
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr) {
        init()
    }


    fun init() {
        paintRate.style = Paint.Style.FILL
        paintRate.color = Color.YELLOW

        paintBg.style = Paint.Style.FILL
        paintBg.color = Color.GRAY
        paintBg.alpha = 150

        paintText.style = Paint.Style.FILL
        paintText.color = Color.BLACK
        paintText.textSize = (height * 0.9).toFloat()
        rateWidth = (width * rate / 100.0).toInt()
        text = "${rate/10}/10.0"
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        rateWidth = (width * rate / 100.0).toInt()
        text = "${rate/10}/10.0"
        paintText.textSize = (height * 0.5).toFloat()
        canvas?.drawRect(0F, 0F, width.toFloat(), height.toFloat(), paintBg)
        canvas?.drawRect(0F, 0F, rateWidth.toFloat(), height.toFloat(), paintRate)
        canvas?.drawText(text, (width/2.5).toFloat(), (height/2).toFloat(), paintText)
        invalidate()
    }
}