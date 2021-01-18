package kz.moviedb.movieapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kz.moviedb.movieapp.model.work_manager.JsonCitation

/**
 * Created by Sarsenov Yerlan on 18.01.2021.
 */
sealed class BaseListItem {
    @Entity(tableName = "citations_table")
    data class RoomCitation(
        @ColumnInfo(name = "author")
        val quoteAuthor: String,
        @ColumnInfo(name = "citation")
        val quoteText: String
    ): BaseListItem() {

        @PrimaryKey(autoGenerate = true)
        var id: Int = 0

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

        companion object {
            fun convertToRoomEntity(citation: JsonCitation): RoomCitation =
                RoomCitation(quoteAuthor = citation.quoteAuthor, quoteText = citation.quoteText)
        }
    }
    data class Search(
        @SerializedName("Poster")val poster: String,
        @SerializedName("Title")val title: String,
        @SerializedName("Type")val type: String,
        @SerializedName("Year")val year: String,
        @SerializedName("imdbID")val imdbID: String
    ): BaseListItem()
    data class ErrorResponse(val message: String): BaseListItem()
}