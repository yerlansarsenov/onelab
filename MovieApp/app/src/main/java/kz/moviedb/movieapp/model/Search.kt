package kz.moviedb.movieapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Search(
        @SerializedName("Poster")val Poster: String,
        @SerializedName("Title")val Title: String,
        @SerializedName("Type")val Type: String,
        @SerializedName("Year")val Year: String,
        @SerializedName("imdbID")val imdbID: String
) : Serializable