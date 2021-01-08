package kz.moviedb.movieapp.ui.search

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kz.moviedb.movieapp.R
import kz.moviedb.movieapp.ui.movies.LIST_OF_MOVIES
import kz.moviedb.movieapp.ui.movies.MoviesActivity
import kz.moviedb.movieapp.utils.hideKeyboard
import kz.moviedb.movieapp.utils.intentFor
import kz.moviedb.movieapp.utils.showToast

const val EMPTY_TEXT_ERROR = "Empty text ERROR!"

class SearchActivity : AppCompatActivity(R.layout.ac_search) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val btnSearch = findViewById<Button>(R.id.search_btn)
        val textSearch = findViewById<EditText>(R.id.search_text)
        btnSearch.setOnClickListener {
            val text = textSearch.text
            if (text.isNotEmpty()) {
                it.hideKeyboard()
                //viewModel.searchMoviesByName(text.toString())
                openMovies(text.toString())
            } else
                showToast(EMPTY_TEXT_ERROR)
        }
    }

    private fun openMovies(name: String) {
        val intent = intentFor<MoviesActivity>(
            LIST_OF_MOVIES to name
        )
        startActivity(intent)
    }

}