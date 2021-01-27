package kz.moviedb.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kz.moviedb.data.model.work_manager.JsonCitation

/**
 * Created by Sarsenov Yerlan on 18.01.2021.
 */
sealed class BaseListItemData {
    @Entity(tableName = "citations_table")
    data class RoomCitationData(
        @PrimaryKey(autoGenerate = true)
        var id: Int = 0,
        @ColumnInfo(name = "author")
        val quoteAuthor: String,
        @ColumnInfo(name = "citation")
        val quoteText: String
    ): BaseListItemData() {

        override fun equals(other: Any?): Boolean {
            if (other !is RoomCitationData)
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
            fun convertToRoomEntity(citation: JsonCitation): RoomCitationData =
                RoomCitationData(quoteAuthor = citation.quoteAuthor, quoteText = citation.quoteText)
        }
    }
    data class SearchData(
        @SerializedName("Poster")val poster: String,
        @SerializedName("Title")val title: String,
        @SerializedName("Type")val type: String,
        @SerializedName("Year")val year: String,
        @SerializedName("imdbID")val imdbID: String
    ): BaseListItemData()
    data class ErrorResponseData(val message: String): BaseListItemData()
}