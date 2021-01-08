package kz.moviedb.movieapp.ui.detail

import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.activity.viewModels
import kz.moviedb.movieapp.R
import kz.moviedb.movieapp.ui.BaseActivity
import kz.moviedb.movieapp.utils.lazyArg
import kz.moviedb.movieapp.utils.setImageWithUrl
import kz.moviedb.movieapp.utils.setImdbRating

/**
 * Created by Sarsenov Yerlan on 07.01.2021.
 */

const val MOVIE_ID = "movie_key"

class DetailActivity : BaseActivity(R.layout.ac_detail) {

    private val movieId by lazyArg<String>(MOVIE_ID)

    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.searchMovieById(movieId)
        viewModel.liveDataHasInternetProblems.observe(this) {
            if (it)
                showError("Some problems with internet")
        }
        viewModel.liveDataLoading.observe(this) { loading ->
            if (loading)
                showLoading()
            else
                hideLoading()
        }
        viewModel.liveDataMovie.observe(this) { response ->
            if (response.Error !== null && response.Error.isNotEmpty())
                showError(response.Error)
            else {
                findViewById<ImageView>(R.id.image_detail).setImageWithUrl(response.Poster)
                findViewById<RatingBar>(R.id.rate_view_detail).setImdbRating(response.imdbRating)
                findViewById<TextView>(R.id.title_view_detail).text = response.Title
                findViewById<TextView>(R.id.relies_view_detail).text = response.Released
                findViewById<TextView>(R.id.writers_tv).text = response.Writer
                findViewById<TextView>(R.id.actors_tv).text = response.Actors
            }
        }
    }

}