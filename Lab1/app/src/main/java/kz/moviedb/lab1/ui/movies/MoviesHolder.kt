package kz.moviedb.lab1.ui.movies

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kz.moviedb.lab1.R
import kz.moviedb.lab1.lesson2_sandbox.setImageWithUrl
import kz.moviedb.lab1.model.Search

/**
 * Created by Sarsenov Yerlan on 25.12.2020.
 */
class MoviesHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(movie: Search, listener: MoviesAdapter.OnClickListener) {
        val imageView = itemView.findViewById<ImageView>(R.id.image_rv)
        imageView.setImageWithUrl(movie.Poster)
        val titleView = itemView.findViewById<TextView>(R.id.title_rv)
        titleView.text = movie.Title
        itemView.setOnClickListener {
            listener.listenOnItemClick(movie.imdbID)
        }
    }

}