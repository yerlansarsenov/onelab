package kz.moviedb.lab1.model

import com.google.gson.annotations.SerializedName

data class SearchResponse(
        @SerializedName("Response")val Response: String,
        @SerializedName("Search")val Search: List<Search>,
        @SerializedName("totalResults")val totalResults: String,
        @SerializedName("Error") val Error: String
)