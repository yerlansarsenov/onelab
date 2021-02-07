package kz.moviedb.presentation.ui.movies

import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kz.moviedb.presentation.R
import kz.moviedb.presentation.model.LoadingState
import kz.moviedb.presentation.model.SearchState
import kz.moviedb.presentation.ui.BaseActivity
import kz.moviedb.presentation.ui.detail.DetailActivity
import kz.moviedb.presentation.ui.detail.MOVIE_ID
import kz.moviedb.presentation.utils.intentFor
import kz.moviedb.presentation.utils.lazyArg
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Sarsenov Yerlan on 07.01.2021.
 */

const val LIST_OF_MOVIES = "list_of_movies"

class MoviesActivity : BaseActivity(R.layout.ac_movies) {

    private val name by lazyArg<String>(LIST_OF_MOVIES)

    private val recyclerView: RecyclerView by lazy { findViewById(R.id.recycler_view) }

    private val viewModel: MoviesViewModel by viewModel()

    private val moviesAdapter: MoviesAdapter by lazy {
        MoviesAdapter(::listenOnItemClick)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recyclerView.apply {
            adapter = moviesAdapter
            layoutManager = LinearLayoutManager(this@MoviesActivity)
            addItemDecoration(
                DividerItemDecoration(
                    this@MoviesActivity,
                    LinearLayoutManager.VERTICAL
                )
            )
        }
        with(viewModel) {
            searchMoviesByName(name)
            liveDataState.observe(this@MoviesActivity) { state ->
                when (state) {
                    is SearchState.ResponseList -> {
                        moviesAdapter.submitList(state.list)
                    }
                    is SearchState.Error -> {
                        showError(state.message)
                    }
                }
            }
            liveDataLoadingState.observe(this@MoviesActivity) { state ->
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