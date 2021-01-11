package kz.moviedb.movieapp.ui.work_manager_ui

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kz.moviedb.movieapp.R

/**
 * Created by Sarsenov Yerlan on 09.01.2021.
 */
class CustomDecoration: RecyclerView.ItemDecoration() {

    private val red: Paint by lazy { Paint() }
    private val green: Paint by lazy { Paint() }
    private val blue: Paint by lazy { Paint() }

    init {
        red.style = Paint.Style.FILL
        red.isAntiAlias = true
        red.color = Color.RED
        green.style = Paint.Style.FILL
        green.isAntiAlias = true
        green.color = Color.GREEN
        blue.style = Paint.Style.FILL
        blue.isAntiAlias = true
        blue.color = Color.BLUE
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight
        val pixelOffSet = parent.context.resources.getDimensionPixelOffset(R.dimen.it_margin)
        val viewCount = parent.childCount
        for (i in 0 until viewCount) {
            val child: View = parent.getChildAt(i)
            val top = child.top - pixelOffSet/2
            val bottom = child.bottom + pixelOffSet/2
            val childAdapterPosition = parent.getChildAdapterPosition(child)
            val rect = Rect(left, top, right, bottom)
            c.drawRect(rect, getPaint(childAdapterPosition))
        }
    }


    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val pixelOffSet: Int = parent.context.resources.getDimensionPixelOffset(R.dimen.it_margin)
        outRect.left = pixelOffSet
        outRect.right = pixelOffSet
        outRect.top = pixelOffSet / 2
        outRect.bottom = pixelOffSet / 2
    }

    private fun getPaintReversed(position: Int): Paint =
        when (position % 3) {
            0 -> blue
            1 -> green
            2 -> red
            else -> blue
        }

    private fun getPaint(position: Int): Paint =
        when (position % 3) {
            0 -> red
            1 -> green
            2 -> blue
            else -> red
        }

}