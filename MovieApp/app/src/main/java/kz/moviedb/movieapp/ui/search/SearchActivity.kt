package kz.moviedb.movieapp.ui.search

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import kz.moviedb.movieapp.R
import kz.moviedb.movieapp.model.MovieResponse
import kz.moviedb.movieapp.model.Search
import kz.moviedb.movieapp.ui.BaseActivity
import kz.moviedb.movieapp.ui.ERROR_TEXT
import kz.moviedb.movieapp.ui.ErrorPage
import kz.moviedb.movieapp.ui.movies.LIST_OF_MOVIES
import kz.moviedb.movieapp.ui.movies.MoviesActivity
import kz.moviedb.movieapp.utils.hideKeyboard
import kz.moviedb.movieapp.utils.intentFor
import kz.moviedb.movieapp.utils.progressDialog
import kz.moviedb.movieapp.utils.showToast

const val EMPTY_TEXT_ERROR = "Empty text ERROR!"

class SearchActivity : BaseActivity(R.layout.ac_search) {

    private val viewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val btnSearch = findViewById<Button>(R.id.search_btn)
        val textSearch = findViewById<EditText>(R.id.search_text)
        viewModel.liveDataSearchResponse.observe(this) { response ->
            // from the server only 2 cases are comes: either Error or list of films
            if (response.Error != null && response.Error.isNotEmpty())
                openError(response.Error)
            else if (response.Search.isNotEmpty())
                openMovies(response.Search)
        }
        viewModel.liveDataLoading.observe(this) {
            if (it)
                showLoading()
            else
                hideLoading()
        }
        viewModel.liveDataHasInternetProblems.observe(this) {
            if (it)
                openError("No access to internet")
        }
        btnSearch.setOnClickListener {
            val text = textSearch.text
            if (text.isNotEmpty()) {
                it.hideKeyboard()
                viewModel.searchMoviesByName(text.toString())
            } else
                showToast(EMPTY_TEXT_ERROR)
        }
    }

    private fun openError(error: String) {
        val intent = intentFor<ErrorPage>(
            ERROR_TEXT to error
        )
        startActivity(intent)
    }

    private fun openMovies(list: List<Search>) {
        val intent = intentFor<MoviesActivity>(
            LIST_OF_MOVIES to list
        )
        startActivity(intent)
    }

    override fun onDestroy() {
        progressDialog = null
        super.onDestroy()
    }

}