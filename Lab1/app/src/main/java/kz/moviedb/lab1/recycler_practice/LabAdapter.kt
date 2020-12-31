package kz.moviedb.lab1.recycler_practice

import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kz.moviedb.lab1.R
import java.io.Serializable

/**
 * Created by Sarsenov Yerlan on 28.12.2020.
 */
class LabAdapter(
val onButtonClickListener: (position: Int) -> Unit, 
val onCheckBoxClickListener: (position: Int, isChecked: Boolean) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var list1: List<GeneralType> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return when (viewType) {
            R.layout.it_recycler_practice_1 -> LabViewHolderSimple(view)
            R.layout.it_recycler_practice_2 -> LabViewHolderButton(onButtonClickListener, view)
            R.layout.it_recycler_practice_3 -> LabViewHolderCheckBox(onCheckBoxClickListener, view)
            else -> throw NotImplementedError()
        }
    }

    override fun getItemViewType(position: Int): Int =
        when(list1[position]) {
            is GeneralType.TypeSimple -> R.layout.it_recycler_practice_1
            is GeneralType.TypeButton -> R.layout.it_recycler_practice_2
            is GeneralType.TypeCheckBox -> R.layout.it_recycler_practice_3
        }


    override fun getItemCount(): Int = list1.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder.itemViewType) {
            R.layout.it_recycler_practice_1 -> (holder as LabViewHolderSimple).bind(list1[position] as GeneralType.TypeSimple)
            R.layout.it_recycler_practice_2 -> (holder as LabViewHolderButton).bind(list1[position] as GeneralType.TypeButton)
            R.layout.it_recycler_practice_3 -> (holder as LabViewHolderCheckBox).bind(list1[position] as GeneralType.TypeCheckBox)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: MutableList<Any>) {
        super.onBindViewHolder(holder, position, payloads)
        if (payloads.isEmpty()) return
        try {
            (holder as LabViewHolderCheckBox).bind(payloads.first() as Boolean)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        when(holder.itemViewType) {
            R.layout.it_recycler_practice_1 -> (holder as LabViewHolderSimple).unbind()
            R.layout.it_recycler_practice_2 -> (holder as LabViewHolderButton).unbind()
            R.layout.it_recycler_practice_3 -> (holder as LabViewHolderCheckBox).unbind()
        }
        super.onViewRecycled(holder)
    }

}

class LabViewHolderSimple(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: GeneralType.TypeSimple) {
        val tv = itemView.findViewById<TextView>(R.id.textview)
        tv.text = "${item.name} ${adapterPosition}"
    }

    fun unbind() {
    }
}

class LabViewHolderButton(
        val onButtonClickListener: (position: Int) -> Unit,
        itemView: View
) : RecyclerView.ViewHolder(itemView) {

    init {
        itemView.findViewById<Button>(R.id.button).setOnClickListener {
            onButtonClickListener.invoke(adapterPosition)
        }
    }

    fun bind(item: GeneralType.TypeButton) {
    }

    fun unbind() {
    }
}

class LabViewHolderCheckBox(
        val onCheckBoxClickListener: (position: Int, isChecked: Boolean) -> Unit,
        itemView: View
) : RecyclerView.ViewHolder(itemView) {

    init {
        val checkBox = itemView.findViewById<CheckBox>(R.id.checkbox)
        /*checkBox.setOnCheckedChangeListener {
            _: CompoundButton, isChecked: Boolean ->
            onCheckBoxClickListener.invoke(adapterPosition, !isChecked)
        }*/
        checkBox.setOnClickListener {
            onCheckBoxClickListener.invoke(adapterPosition, (it as CheckBox).isChecked)
        }
    }

    fun bind(item: GeneralType.TypeCheckBox) {
        val checkBox = itemView.findViewById<CheckBox>(R.id.checkbox)
        checkBox.isChecked = item.isChecked
        checkBox.text = "${item.name} ${adapterPosition}"
    }

    fun bind(isChecked: Boolean) {
        val checkBox = itemView.findViewById<CheckBox>(R.id.checkbox)
        checkBox.isChecked = isChecked
    }

    fun unbind() {
        val checkBox = itemView.findViewById<CheckBox>(R.id.checkbox)
        checkBox.isChecked = false
        checkBox.text = ""
    }
}

sealed class GeneralType {
    data class TypeSimple(var name: String?): GeneralType()
    class TypeButton : GeneralType()
    data class TypeCheckBox(val name: String, var isChecked: Boolean = false) : GeneralType()
}