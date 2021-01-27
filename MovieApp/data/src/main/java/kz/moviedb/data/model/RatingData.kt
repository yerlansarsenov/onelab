package kz.moviedb.data.model

import com.google.gson.annotations.SerializedName

data class RatingData(
    @SerializedName("Source") val source: String,
    @SerializedName("Value") val value: String
)