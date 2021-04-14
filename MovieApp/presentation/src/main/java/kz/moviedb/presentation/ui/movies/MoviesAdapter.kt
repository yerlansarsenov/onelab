package kz.moviedb.presentation.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kz.moviedb.domain.model.BaseListItem
import kz.moviedb.presentation.R

/**
 * Created by Sarsenov Yerlan on 25.12.2020.
 */
class MoviesAdapter(
    private val listener: (id: String) -> Unit
) : ListAdapter<BaseListItem, RecyclerView.ViewHolder>(SearchItemCallBack()) {

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
            else -> -1
        }
}

