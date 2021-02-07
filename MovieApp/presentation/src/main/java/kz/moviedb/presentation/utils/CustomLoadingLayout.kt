package kz.moviedb.presentation.utils

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView

/**
 * Created by Sarsenov Yerlan on 01.02.2021.
 */

private const val LOADING_TEXT = "Loading..."

class CustomLoadingLayout: LinearLayout {

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        init()
    }

    private fun init() {
        val padding = 30
        this.orientation = HORIZONTAL
        this.setPadding(padding, padding, padding, padding)
        this.gravity = Gravity.CENTER
        val llParam = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT)
        llParam.apply {
            llParam.gravity = Gravity.CENTER
        }
        this.layoutParams = llParam
        val progressBar : ProgressBar = ProgressBar(context)
        progressBar.apply {
            isIndeterminate = true
            setPadding(0, 0, padding, 0)
            layoutParams = llParam
        }
        val textView : TextView = TextView(context)
        textView.apply {
            text = LOADING_TEXT
            setTextColor(Color.parseColor("#000000"))
            textSize = 20F
            layoutParams = llParam
        }
        this.addView(progressBar)
        this.addView(textView)
    }

}