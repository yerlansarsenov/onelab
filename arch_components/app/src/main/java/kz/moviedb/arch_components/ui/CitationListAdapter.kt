package kz.moviedb.arch_components.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kz.moviedb.arch_components.R
import kz.moviedb.arch_components.model.JsonCitation
import kz.moviedb.arch_components.model.RoomCitation

/**
 * Created by Sarsenov Yerlan on 09.01.2021.
 */
class CitationListAdapter : ListAdapter<RoomCitation, CitationViewHolder>(CitationDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.it_citation, parent, false)
        return CitationViewHolder(view)
    }

    override fun onBindViewHolder(holder: CitationViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}

class CitationDiffUtil: DiffUtil.ItemCallback<RoomCitation>() {
    override fun areItemsTheSame(oldItem: RoomCitation, newItem: RoomCitation): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: RoomCitation, newItem: RoomCitation): Boolean =
        oldItem == newItem

}

class CitationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item : RoomCitation) {
        itemView.findViewById<TextView>(R.id.citation_text).text = item.quoteText
        itemView.findViewById<TextView>(R.id.citation_author).text = item.quoteAuthor
    }
}