package kz.moviedb.movieapp.ui.detail

import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import kz.moviedb.movieapp.R
import kz.moviedb.movieapp.ui.BaseActivity
import kz.moviedb.movieapp.utils.lazyArg
import kz.moviedb.movieapp.utils.setImageWithUrl
import kz.moviedb.movieapp.utils.setImdbRating
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Sarsenov Yerlan on 07.01.2021.
 */

const val MOVIE_ID = "movie_key"

class DetailActivity : BaseActivity(R.layout.ac_detail) {

    private val movieId by lazyArg<String>(MOVIE_ID)

    private val viewModel: DetailViewModel by viewModel()

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
            if (response.error !== null && response.error.isNotEmpty())
                showError(response.error)
            else {
                findViewById<ImageView>(R.id.image_detail).setImageWithUrl(response.poster)
                findViewById<RatingBar>(R.id.rate_view_detail).setImdbRating(response.imdbRating)
                findViewById<TextView>(R.id.title_view_detail).text = response.title
                findViewById<TextView>(R.id.relies_view_detail).text = response.released
                findViewById<TextView>(R.id.writers_tv).text = response.writer
                findViewById<TextView>(R.id.actors_tv).text = response.actors
            }
        }
    }

}