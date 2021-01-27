package kz.moviedb.presentation.ui.movies

import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kz.moviedb.presentation.R
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

    private val adapter: MoviesAdapter by lazy {
        MoviesAdapter(::listenOnItemClick)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )
        viewModel.searchMoviesByName(name)
        viewModel.liveDataState.observe(this) { state ->
            when (state) {
                is SearchState.ShowLoading -> {
                    showLoading()
                }
                is SearchState.ResponseList -> {
                    adapter.submitList(state.list)
                }
                is SearchState.Error -> {
                    showError(state.message)
                }
                is SearchState.HideLoading -> {
                    hideLoading()
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