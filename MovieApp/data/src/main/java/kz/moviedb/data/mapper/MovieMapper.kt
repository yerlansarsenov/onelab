package kz.moviedb.data.mapper

import android.widget.TextView
import kz.moviedb.data.model.MovieResponseData
import kz.moviedb.data.model.RatingData
import kz.moviedb.domain.mapper.Mapper
import kz.moviedb.domain.model.MovieResponse
import kz.moviedb.domain.model.Rating

/**
 * Created by Sarsenov Yerlan on 27.01.2021.
 */
class MovieMapper(private val ratingMapper: RatingMapper) : Mapper<MovieResponseData, MovieResponse> {

    override fun convert(model: MovieResponseData): MovieResponse {
        val writers = model.writer.split(',')
        val actors = model.actors.split(',')
        return MovieResponse(
            actors = actors,
            plot = model.plot,
            poster = model.poster,
            ratings = model.ratings.map { ratingMapper.convert(it) },
            released = model.released,
            title = model.title,
            writer = writers,
            imdbRating = model.imdbRating,
        )
    }

}

class RatingMapper : Mapper<RatingData, Rating> {

    override fun convert(model: RatingData): Rating {
        val value = RatingRecognizerHelper().getValueFloat(model.value)
        return Rating(
            source = model.source,
            value = value
        )
    }

    inner class RatingRecognizerHelper {
        fun getValueFloat(value: String): Float {
            if (value.contains("%")) {
                return try {
                    value.substring(0, value.length-1).toFloat()
                } catch (e: Exception) {
                    0F
                }
            }
            return try {
                val nums = value.split('/').map { it.toFloat() }
                if (nums[1] == 100.toFloat()) {
                    nums[0].toFloat()
                } else {
                    (nums[0] * 10).toFloat()
                }
            } catch (e: Exception) {
                0F
            }
        }
    }

}