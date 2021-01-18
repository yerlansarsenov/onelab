package kz.moviedb.movieapp.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kz.moviedb.movieapp.R
import kz.moviedb.movieapp.model.BaseListItem
import kz.moviedb.movieapp.model.BaseListItem.ErrorResponse
import kz.moviedb.movieapp.model.BaseListItem.RoomCitation
import kz.moviedb.movieapp.model.BaseListItem.Search

/**
 * Created by Sarsenov Yerlan on 25.12.2020.
 */
class MoviesAdapter(
    private val listener: (id: String) -> Unit
) : ListAdapter<BaseListItem, RecyclerView.ViewHolder>(SearchDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return when (viewType) {
            R.layout.it_movie -> MoviesHolder(view)
            R.layout.it_citation -> CitationViewHolder(view)
            else -> throw NotImplementedError()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (currentList[position]) {
            is Search -> (holder as MoviesHolder).bind(currentList[position] as Search, listener)
            is RoomCitation -> (holder as CitationViewHolder).bind(currentList[position] as RoomCitation)
            is ErrorResponse -> { /* do nothing */ }
        }
    }

    override fun getItemViewType(position: Int): Int =
        when (currentList[position]) {
            is Search -> R.layout.it_movie
            is RoomCitation -> R.layout.it_citation
            else -> -1
        }
}

class SearchDiffUtil : DiffUtil.ItemCallback<BaseListItem>() {
    override fun areItemsTheSame(oldItem: BaseListItem, newItem: BaseListItem): Boolean {
        if (oldItem is Search && newItem is Search) {
            return oldItem.imdbID == newItem.imdbID
        }
        if (oldItem is RoomCitation && newItem is RoomCitation) {
            return oldItem.id == newItem.id
        }
        return false
    }

    override fun areContentsTheSame(oldItem: BaseListItem, newItem: BaseListItem): Boolean {
        if (oldItem is Search && newItem is Search) {
            return oldItem.title == newItem.title
        }
        if (oldItem is RoomCitation && newItem is RoomCitation) {
            return oldItem.quoteText == newItem.quoteText || oldItem.quoteAuthor == newItem.quoteAuthor
        }
        return false
    }

}