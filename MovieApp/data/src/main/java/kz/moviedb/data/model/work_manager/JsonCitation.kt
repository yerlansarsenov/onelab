package kz.moviedb.data.model.work_manager


import com.google.gson.annotations.SerializedName


data class JsonCitation(
    @SerializedName("quoteAuthor")
    val quoteAuthor: String,
    @SerializedName("quoteLink")
    val quoteLink: String,
    @SerializedName("quoteText")
    val quoteText: String,
    @SerializedName("senderLink")
    val senderLink: String,
    @SerializedName("senderName")
    val senderName: String
)