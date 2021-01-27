package kz.moviedb.presentation.ui.movies

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kz.moviedb.domain.model.BaseListItem
import kz.moviedb.presentation.R
import kz.moviedb.presentation.utils.setImageWithUrl

/**
 * Created by Sarsenov Yerlan on 25.12.2020.
 */
class MoviesHolder(iV: View) : RecyclerView.ViewHolder(iV) {

    fun bind(movie: BaseListItem.Search, listener: (id: String) -> Unit) {
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
