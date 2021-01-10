package kz.moviedb.arch_components.ui

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kz.moviedb.arch_components.R

/**
 * Created by Sarsenov Yerlan on 09.01.2021.
 */
class CustomDecoration: RecyclerView.ItemDecoration() {

    private val red: Paint by lazy { Paint() }
    private val green: Paint by lazy { Paint() }
    private val blue: Paint by lazy { Paint() }

    init {
        red.style = Paint.Style.STROKE
        red.isAntiAlias = true
        red.color = Color.RED
        green.style = Paint.Style.STROKE
        green.isAntiAlias = true
        green.color = Color.GREEN
        blue.style = Paint.Style.STROKE
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
            val child = parent.getChildAt(i)
            val top = child.top - pixelOffSet/2
            val bottom = child.bottom + pixelOffSet/2
            val childAdapterPosition = parent.getChildAdapterPosition(child)
            c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), getPaint(childAdapterPosition))
        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        val pixelOffSet = parent.context.resources.getDimensionPixelOffset(R.dimen.it_margin)
        val viewCount = parent.childCount
        val right = parent.width / 2
        val left = right - pixelOffSet*2
        for (i in 0 until viewCount) {
            val child = parent.getChildAt(i)
            val top = child.top + pixelOffSet
            val bottom = child.bottom - pixelOffSet
            val childAdapterPosition = parent.getChildAdapterPosition(child)
            c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), getPaintReversed(childAdapterPosition))
        }
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val pixelOffSet = parent.context.resources.getDimensionPixelOffset(R.dimen.it_margin)
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