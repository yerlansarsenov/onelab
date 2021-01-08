package kz.moviedb.movieapp.ui.movies

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kz.moviedb.movieapp.R
import kz.moviedb.movieapp.ui.BaseActivity
import kz.moviedb.movieapp.ui.detail.DetailActivity
import kz.moviedb.movieapp.ui.detail.MOVIE_ID
import kz.moviedb.movieapp.utils.intentFor
import kz.moviedb.movieapp.utils.lazyArg

/**
 * Created by Sarsenov Yerlan on 07.01.2021.
 */

const val LIST_OF_MOVIES = "list_of_movies"

class MoviesActivity : BaseActivity(R.layout.ac_movies) {

    private val name by lazyArg<String>(LIST_OF_MOVIES)

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
        viewModel.searchMoviesByName(name)
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
        viewModel.liveDataSearchResponse.observe(this) { response ->
            if (response.Error !== null && response.Error.isNotEmpty())
                showError(response.Error)
            else
                adapter.submitList(response.Search)
        }
    }

    private fun listenOnItemClick(imdbId: String) {
        val intent = intentFor<DetailActivity>(
            MOVIE_ID to imdbId
        )
        startActivity(intent)
    }

    override fun onDestroy() {
        recyclerView.adapter = null
        recyclerView.layoutManager = null
        super.onDestroy()
    }

}