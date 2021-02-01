package kz.moviedb.presentation.ui.detail

import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kz.moviedb.domain.model.MovieResponse
import kz.moviedb.presentation.R
import kz.moviedb.presentation.model.LoadingState
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

    private val ratingRecyclerView: RecyclerView by lazy { findViewById(R.id.ratings_rv) }

    private val adapter: RatingAdapter by lazy { RatingAdapter() }

    private val viewModel: DetailViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ratingRecyclerView.adapter = adapter
        ratingRecyclerView.layoutManager = LinearLayoutManager(this)
        ratingRecyclerView.isNestedScrollingEnabled = false
        viewModel.searchMovieById(movieId)
        viewModel.liveDataMovie.observe(this) { state ->
            when (state) {
                is MovieState.Response -> {
                    setMovie(state.movie)
                }
                is MovieState.Error -> {
                    showError(state.message)
                }
            }
        }
        viewModel.liveDataLoadingState.observe(this) { state ->
            when (state) {
                LoadingState.ShowLoading -> {
                    showLoading()
                }
                LoadingState.HideLoading -> {
                    hideLoading()
                }
                null -> {}
            }
        }
    }

    private fun setMovie(movie: MovieResponse) {
        findViewById<ImageView>(R.id.image_detail).setImageWithUrl(movie.poster)
        findViewById<RatingBar>(R.id.rate_view_detail).setImdbRating(movie.imdbRating)
        findViewById<TextView>(R.id.title_view_detail).text = movie.title
        findViewById<TextView>(R.id.relies_view_detail).text = movie.released
        val writersTextView = findViewById<TextView>(R.id.writers_tv)
        for (w in movie.writer) {
            writersTextView.text = "${writersTextView.text}$w\n"
        }
        val actorsTextView = findViewById<TextView>(R.id.actors_tv)
        for (a in movie.actors) {
            actorsTextView.text = "${actorsTextView.text}$a\n"
        }
        findViewById<TextView>(R.id.over_detail).text = movie.plot
        val list = movie.ratings
        adapter.submitList(list)
    }

    override fun onDestroy() {
        super.onDestroy()
        ratingRecyclerView.layoutManager = null
        ratingRecyclerView.adapter = null
    }

}