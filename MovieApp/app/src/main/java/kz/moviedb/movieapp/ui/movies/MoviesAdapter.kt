package kz.moviedb.movieapp.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import kz.moviedb.movieapp.R
import kz.moviedb.movieapp.model.Search

/**
 * Created by Sarsenov Yerlan on 25.12.2020.
 */
class MoviesAdapter(
    private val listener: (id: String) -> Unit
) : ListAdapter<Search, MoviesHolder>(SearchDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.it_movie, parent, false)
        return MoviesHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesHolder, position: Int) {
        holder.bind(currentList[position], listener)
    }

}

class SearchDiffUtil : DiffUtil.ItemCallback<Search>() {
    override fun areItemsTheSame(oldItem: Search, newItem: Search): Boolean = oldItem.imdbID == newItem.imdbID

    override fun areContentsTheSame(oldItem: Search, newItem: Search): Boolean = oldItem == newItem

}