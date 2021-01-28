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
class MovieMapper(private val ratingMapper: RatingMapper) : Mapper<MovieResponse, MovieResponseData> {
    override fun convert(model: MovieResponse): MovieResponseData {
        var writersText = ""
        for (w in model.writer) {
            writersText = "${writersText}$w\n"
        }
        var actorsText = ""
        for (w in model.actors) {
            actorsText = "${actorsText}$w\n"
        }
        return MovieResponseData(
            actors = actorsText,
            awards = model.awards,
            boxOffice = model.boxOffice,
            country = model.country,
            dvd = model.dvd,
            director = model.director,
            genre = model.genre,
            language = model.language,
            metascore = model.metascore,
            plot = model.plot,
            poster = model.poster,
            production = model.production,
            rated = model.rated,
            ratings = model.ratings.map { ratingMapper.convert(it) },
            released = model.released,
            response = model.response,
            runtime = model.runtime,
            title = model.title,
            type = model.type,
            website = model.website,
            writer = writersText,
            year = model.year,
            imdbID = model.imdbID,
            imdbRating = model.imdbRating,
            imdbVotes = model.imdbVotes,
            error = model.error
        )
    }

    override fun invert(model: MovieResponseData): MovieResponse {
        val writers = model.writer.split(',')
        val actors = model.actors.split(',')
        return MovieResponse(
            actors = actors,
            awards = model.awards,
            boxOffice = model.boxOffice,
            country = model.country,
            dvd = model.dvd,
            director = model.director,
            genre = model.genre,
            language = model.language,
            metascore = model.metascore,
            plot = model.plot,
            poster = model.poster,
            production = model.production,
            rated = model.rated,
            ratings = model.ratings.map { ratingMapper.invert(it) },
            released = model.released,
            response = model.response,
            runtime = model.runtime,
            title = model.title,
            type = model.type,
            website = model.website,
            writer = writers,
            year = model.year,
            imdbID = model.imdbID,
            imdbRating = model.imdbRating,
            imdbVotes = model.imdbVotes,
            error = model.error
        )
    }

}

class RatingMapper : Mapper<Rating, RatingData> {
    override fun convert(model: Rating): RatingData {
        return RatingData(
            source = model.source,
            value = model.value
        )
    }

    override fun invert(model: RatingData): Rating {
        return Rating(
            source = model.source,
            value = model.value
        )
    }

}