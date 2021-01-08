package kz.moviedb.movieapp.ui.movies

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kz.moviedb.movieapp.R
import kz.moviedb.movieapp.model.MovieResponse
import kz.moviedb.movieapp.model.Search
import kz.moviedb.movieapp.ui.BaseActivity
import kz.moviedb.movieapp.ui.detail.DetailActivity
import kz.moviedb.movieapp.ui.detail.MOVIE_KEY
import kz.moviedb.movieapp.utils.intentFor
import kz.moviedb.movieapp.utils.lazyArg
import kz.moviedb.movieapp.utils.progressDialog
import kz.moviedb.movieapp.utils.showToast

/**
 * Created by Sarsenov Yerlan on 07.01.2021.
 */

const val LIST_OF_MOVIES = "list_of_movies"

class MoviesActivity : BaseActivity(R.layout.ac_movies) {

    private val listOfMovies by lazyArg<List<Search>>(LIST_OF_MOVIES)

    lateinit var recyclerView: RecyclerView

    private val viewModel: MoviesViewModel by viewModels()

    private val adapter: MoviesAdapter by lazy {
        MoviesAdapter(::listenOnItemClick)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )
        adapter.submitList(listOfMovies)
        viewModel.liveDataHasInternetProblems.observe(this) {
            if (it)
                showError("Some problems with internet")
        }
        viewModel.liveDataLoading.observe(this) {
            if (it)
                showLoading()
            else
                hideLoading()
        }
        viewModel.liveDataMovie.observe(this) { response ->
            if (response.Error !== null && response.Error.isNotEmpty())
                showError(response.Error)
            else
                openMovieDetail(response)
        }
    }

    private fun listenOnItemClick(imdbId: String) {
        viewModel.searchMovieById(imdbId)
    }

    override fun onDestroy() {
        recyclerView.adapter = null
        recyclerView.layoutManager = null
        super.onDestroy()
    }

    private fun showError(error: String) {
        showToast(error)
    }

    private fun openMovieDetail(movieResponse: MovieResponse) {
        val intent = intentFor<DetailActivity>(
            MOVIE_KEY to movieResponse
        )
        startActivity(intent)
    }

}