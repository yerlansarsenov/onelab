package kz.moviedb.lab1.recycler_practice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kz.moviedb.lab1.R

/**
 * Created by Sarsenov Yerlan on 28.12.2020.
 */
class LabAdapter(val onClickListener: (position: Int) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var list1: List<GeneralType> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return when (viewType) {
            0 -> LabViewHolder1(view)
            1 -> LabViewHolder2(view)
            else -> return throw NotImplementedError()
        }
    }

    override fun getItemViewType(position: Int): Int =
        when(list1[position]) {
            is GeneralType.Type1 -> 0
            is GeneralType.Type2 -> 1
        }


    override fun getItemCount(): Int = list1.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder.itemViewType) {
            0 -> (holder as LabViewHolder1).bind(list1[position])
            1 -> (holder as LabViewHolder2).bind(list1[position])
        }
    }

}

class LabViewHolder1(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: GeneralType) {
        val tv = itemView.findViewById<TextView>(R.id.textview_)
        tv.text = (item as GeneralType.Type1).name
    }

}

class LabViewHolder2(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: GeneralType) {
        val tv = itemView.findViewById<TextView>(R.id.textview_)
        tv.text = (item as GeneralType.Type2).name
    }

}

sealed class GeneralType {
    data class Type1(val name: String): GeneralType()
    data class Type2(val name: String): GeneralType()
}