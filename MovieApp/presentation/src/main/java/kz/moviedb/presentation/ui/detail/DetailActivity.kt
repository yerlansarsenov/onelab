package kz.moviedb.presentation.ui.detail

import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import kz.moviedb.presentation.R
import kz.moviedb.presentation.model.MovieState
import kz.moviedb.presentation.ui.BaseActivity
import kz.moviedb.presentation.utils.lazyArg
import kz.moviedb.presentation.utils.setImageWithUrl
import kz.moviedb.presentation.utils.setImdbRating
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
        viewModel.liveDataMovie.observe(this) { state ->
            when (state) {
                is MovieState.ShowLoading -> {
                    showLoading()
                }
                is MovieState.Response -> {
                    findViewById<ImageView>(R.id.image_detail).setImageWithUrl(state.movie.poster)
                    findViewById<RatingBar>(R.id.rate_view_detail).setImdbRating(state.movie.imdbRating)
                    findViewById<TextView>(R.id.title_view_detail).text = state.movie.title
                    findViewById<TextView>(R.id.relies_view_detail).text = state.movie.released
                    findViewById<TextView>(R.id.writers_tv).text = state.movie.writer
                    findViewById<TextView>(R.id.actors_tv).text = state.movie.actors
                }
                is MovieState.Error -> {
                    showError(state.message)
                }
                is MovieState.HideLoading -> {
                    hideLoading()
                }
            }
        }
    }

}