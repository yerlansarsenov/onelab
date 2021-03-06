package kz.moviedb.presentation.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
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


    private fun init() {
        paintRate.apply {
            style = Paint.Style.FILL
            color = Color.YELLOW
        }
        paintBg.apply {
            style = Paint.Style.FILL
            color = Color.GRAY
            alpha = 150
        }
        paintText.apply {
            style = Paint.Style.FILL
            color = Color.BLACK
            textSize = (height * 0.9).toFloat()
        }
        rateWidth = (width * rate / 100.0).toInt()
        text = "${rate/10}/10.0"
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        rateWidth = (width * rate / 100.0).toInt()
        text = "${rate/10}/10.0"
        paintText.textSize = (height * 0.5).toFloat()
        canvas?.let {
            it.drawRect(0F, 0F, width.toFloat(), height.toFloat(), paintBg)
            it.drawRect(0F, 0F, rateWidth.toFloat(), height.toFloat(), paintRate)
            it.drawText(text, (width/2.5).toFloat(), (height/2).toFloat(), paintText)
        }
    }
}