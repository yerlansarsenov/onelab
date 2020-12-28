package kz.moviedb.lab1.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kz.moviedb.lab1.R
import kz.moviedb.lab1.model.Search

/**
 * Created by Sarsenov Yerlan on 25.12.2020.
 */
class MoviesAdapter(var list: List<Search>, var listener: OnClickListener) : RecyclerView.Adapter<MoviesHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.it_movie, parent, false)
        return MoviesHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesHolder, position: Int) {
        holder.bind(list[position], listener)
    }

    override fun getItemCount(): Int = list.size

    interface OnClickListener {
        fun listenOnItemClick(imdbId: String)
    }
}

