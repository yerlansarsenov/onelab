package kz.moviedb.movieapp.ui.detail

import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kz.moviedb.movieapp.R
import kz.moviedb.movieapp.model.MovieResponse
import kz.moviedb.movieapp.utils.lazyArg
import kz.moviedb.movieapp.utils.setImageWithUrl
import kz.moviedb.movieapp.utils.setImdbRating

/**
 * Created by Sarsenov Yerlan on 07.01.2021.
 */

const val MOVIE_KEY = "movie_key"

class DetailActivity : AppCompatActivity(R.layout.ac_detail) {

    private val movie by lazyArg<MovieResponse>(MOVIE_KEY)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findViewById<ImageView>(R.id.image_detail).setImageWithUrl(movie.Poster)
        findViewById<RatingBar>(R.id.rate_view_detail).setImdbRating(movie.imdbRating)
        findViewById<TextView>(R.id.title_view_detail).text = movie.Title
        findViewById<TextView>(R.id.relies_view_detail).text = movie.Released
        findViewById<TextView>(R.id.writers_tv).text = movie.Writer
        findViewById<TextView>(R.id.actors_tv).text = movie.Actors
    }

}