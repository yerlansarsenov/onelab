package kz.moviedb.domain.model


/**
 * Created by Sarsenov Yerlan on 18.01.2021.
 */

sealed class BaseListItem {
    data class RoomCitation(
        var id: Int = 0,
        val quoteAuthor: String,
        val quoteText: String
    ): BaseListItem() {

        override fun equals(other: Any?): Boolean {
            if (other !is RoomCitation)
                return false
            return other.quoteText == this.quoteText
        }
        override fun hashCode(): Int {
            var result = id
            result = 31 * result + quoteAuthor.hashCode()
            result = 31 * result + quoteText.hashCode()
            return result
        }
    }
    data class Search(
        val poster: String,
        val title: String,
//        val type: String,
//        val year: String,
        val imdbID: String
    ): BaseListItem()
}