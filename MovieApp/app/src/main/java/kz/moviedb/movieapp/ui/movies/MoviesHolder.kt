package kz.moviedb.movieapp.ui.movies

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kz.moviedb.movieapp.R
import kz.moviedb.movieapp.model.Search
import kz.moviedb.movieapp.utils.setImageWithUrl

/**
 * Created by Sarsenov Yerlan on 25.12.2020.
 */
class MoviesHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(movie: Search, listener: (id: String) -> Unit) {
        val imageView = itemView.findViewById<ImageView>(R.id.image_rv)
        imageView.setImageWithUrl(movie.Poster)
        val titleView = itemView.findViewById<TextView>(R.id.title_rv)
        titleView.text = movie.Title
        itemView.setOnClickListener {
            listener(movie.imdbID)
        }
        itemView.animate()
                .rotation(360f)
                .setDuration(300)
                .start()
    }

}