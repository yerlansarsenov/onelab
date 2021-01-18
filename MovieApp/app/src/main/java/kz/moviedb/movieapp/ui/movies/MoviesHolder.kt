package kz.moviedb.movieapp.ui.movies

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kz.moviedb.movieapp.R
import kz.moviedb.movieapp.model.BaseListItem
import kz.moviedb.movieapp.model.BaseListItem.Search
import kz.moviedb.movieapp.utils.setImageWithUrl

/**
 * Created by Sarsenov Yerlan on 25.12.2020.
 */
class MoviesHolder(iV: View) : RecyclerView.ViewHolder(iV) {

    fun bind(movie: Search, listener: (id: String) -> Unit) {
        val imageView = itemView.findViewById<ImageView>(R.id.image_rv)
        imageView.setImageWithUrl(movie.poster)
        val titleView = itemView.findViewById<TextView>(R.id.title_rv)
        titleView.text = movie.title
        itemView.setOnClickListener {
            listener(movie.imdbID)
        }
        itemView.animate()
                .rotation(360f)
                .setDuration(300)
                .start()
    }

}

class CitationViewHolder(iV: View) : RecyclerView.ViewHolder(iV) {
    fun bind(item : BaseListItem.RoomCitation) {
        itemView.findViewById<TextView>(R.id.citation_text).text = item.quoteText
        itemView.findViewById<TextView>(R.id.citation_author).text = item.quoteAuthor
    }
}
