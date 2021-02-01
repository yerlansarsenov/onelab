package kz.moviedb.presentation.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kz.moviedb.domain.model.BaseListItem
import kz.moviedb.presentation.R

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
            is BaseListItem.Search -> (holder as MoviesHolder).bind(currentList[position] as BaseListItem.Search, listener)
            is BaseListItem.RoomCitation -> (holder as CitationViewHolder).bind(currentList[position] as BaseListItem.RoomCitation)
        }
    }

    override fun getItemViewType(position: Int): Int =
        when (currentList[position]) {
            is BaseListItem.Search -> R.layout.it_movie
            is BaseListItem.RoomCitation -> R.layout.it_citation
        }
}

class SearchDiffUtil : DiffUtil.ItemCallback<BaseListItem>() {
    override fun areItemsTheSame(oldItem: BaseListItem, newItem: BaseListItem): Boolean {
        if (oldItem is BaseListItem.Search && newItem is BaseListItem.Search) {
            return oldItem.imdbID == newItem.imdbID
        }
        if (oldItem is BaseListItem.RoomCitation && newItem is BaseListItem.RoomCitation) {
            return oldItem.id == newItem.id
        }
        return false
    }

    override fun areContentsTheSame(oldItem: BaseListItem, newItem: BaseListItem): Boolean {
        if (oldItem is BaseListItem.Search && newItem is BaseListItem.Search) {
            return oldItem.title == newItem.title
        }
        if (oldItem is BaseListItem.RoomCitation && newItem is BaseListItem.RoomCitation) {
            return oldItem.quoteText == newItem.quoteText || oldItem.quoteAuthor == newItem.quoteAuthor
        }
        return false
    }

}