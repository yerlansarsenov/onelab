package kz.moviedb.lab1.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Rating(
    @SerializedName("Source") val Source: String,
    @SerializedName("Value") val Value: String
) : Serializable