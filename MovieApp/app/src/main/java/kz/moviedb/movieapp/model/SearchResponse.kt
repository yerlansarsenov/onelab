package kz.moviedb.movieapp.model

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("Response")val response: String,
    @SerializedName("Search")val search: List<BaseListItem.Search>,
    @SerializedName("totalResults")val totalResults: String,
    @SerializedName("Error") val error: String?
)