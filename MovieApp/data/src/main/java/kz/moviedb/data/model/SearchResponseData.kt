package kz.moviedb.data.model

import com.google.gson.annotations.SerializedName

data class SearchResponseData(
    @SerializedName("Response")val response: String,
    @SerializedName("Search")val search: List<BaseListItemData.SearchData>,
    @SerializedName("totalResults")val totalResults: String,
    @SerializedName("Error") val error: String?
)