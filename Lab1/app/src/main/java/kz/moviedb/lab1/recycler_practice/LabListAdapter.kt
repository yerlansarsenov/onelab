package kz.moviedb.lab1.recycler_practice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kz.moviedb.lab1.R

/**
 * Created by Sarsenov Yerlan on 31.12.2020.
 */
class LabListAdapter(
        val onButtonClickListener: (position: Int) -> Unit,
        val onCheckBoxClickListener: (position: Int, isChecked: Boolean) -> Unit,
        val onLongClickListener: (position: Int) -> Boolean
) : ListAdapter<GeneralType, RecyclerView.ViewHolder>(LabDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return when (viewType) {
            R.layout.it_recycler_practice_1 -> LabViewHolderSimpleLongClick(onLongClickListener, view)
            R.layout.it_recycler_practice_2 -> LabViewHolderButton(onButtonClickListener, view)
            R.layout.it_recycler_practice_3 -> LabViewHolderCheckBox(onCheckBoxClickListener, view)
            else -> throw NotImplementedError()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(currentList[position]) {
            is GeneralType.TypeSimple -> R.layout.it_recycler_practice_1
            is GeneralType.TypeButton -> R.layout.it_recycler_practice_2
            is GeneralType.TypeCheckBox -> R.layout.it_recycler_practice_3
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder.itemViewType) {
            R.layout.it_recycler_practice_1 -> (holder as LabViewHolderSimpleLongClick).bind(currentList[position] as GeneralType.TypeSimple)
            R.layout.it_recycler_practice_2 -> (holder as LabViewHolderButton).bind(currentList[position] as GeneralType.TypeButton)
            R.layout.it_recycler_practice_3 -> (holder as LabViewHolderCheckBox).bind(currentList[position] as GeneralType.TypeCheckBox)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: MutableList<Any>) {
        super.onBindViewHolder(holder, position, payloads)
        if (payloads.isEmpty()) return
        try {
            (holder as LabViewHolderSimpleLongClick).bind(payloads.first() as String)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}

class LabDiffUtil: DiffUtil.ItemCallback<GeneralType>() {
    override fun areItemsTheSame(oldItem: GeneralType, newItem: GeneralType): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: GeneralType, newItem: GeneralType): Boolean {
        return oldItem == newItem
    }

}

class LabViewHolderSimpleLongClick(
        val onLongClickListener: (position: Int) -> Boolean,
        itemView: View) : RecyclerView.ViewHolder(itemView
) {
    fun bind(item: GeneralType.TypeSimple) {
        val tv = itemView.findViewById<TextView>(R.id.textview)
        tv.text = "${item.name} ${adapterPosition}"
        itemView.setOnLongClickListener {
            return@setOnLongClickListener onLongClickListener.invoke(adapterPosition)
        }
    }

    fun bind(name: String) {
        val textView = itemView.findViewById<TextView>(R.id.textview)
        textView.text = name
    }

    fun unbind() {
    }
}