package kz.moviedb.presentation.model

import android.util.Log
import kz.moviedb.domain.mapper.Mapper
import kz.moviedb.domain.model.Rating
import java.lang.NumberFormatException

/**
 * Created by Sarsenov Yerlan on 28.01.2021.
 */

private const val TAG = "RatingWithValue"

data class RatingWithValue(
    val source: String,
    val value: Float
)

class RatingMapperUI: Mapper<Rating, RatingWithValue>{
    override fun convert(model: Rating): RatingWithValue {
        val value = RatingRecognizerHelper().getValueFloat(model.value.toString())
        return RatingWithValue(
            source = model.source,
            value = value
        )
    }

    override fun invert(model: RatingWithValue): Rating {
        return Rating(
            source = model.source,
            value = model.value.toString()
        )
    }

}

