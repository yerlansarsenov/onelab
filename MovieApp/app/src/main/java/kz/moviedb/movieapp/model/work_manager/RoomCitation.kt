package kz.moviedb.movieapp.model.work_manager

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Sarsenov Yerlan on 10.01.2021.
 */

@Entity(tableName = "citations_table")
data class RoomCitation(
    @ColumnInfo(name = "author")
    val quoteAuthor: String,
    @ColumnInfo(name = "citation")
    val quoteText: String
) {

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