package kz.moviedb.presentation.ui.search

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kz.moviedb.presentation.R
import kz.moviedb.presentation.ui.movies.LIST_OF_MOVIES
import kz.moviedb.presentation.ui.movies.MoviesActivity
import kz.moviedb.presentation.utils.hideKeyboard
import kz.moviedb.presentation.utils.intentFor
import kz.moviedb.presentation.utils.showToast

const val EMPTY_TEXT_ERROR = "Empty text ERROR!"

class SearchActivity : AppCompatActivity(R.layout.ac_search) {

    private val textSearch: EditText by lazy {
        findViewById(R.id.search_text)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val btnSearch = findViewById<Button>(R.id.search_btn)
        textSearch.setOnEditorActionListener { view, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchMovie(view)
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
        btnSearch.setOnClickListener { view ->
            searchMovie(view)
        }
    }

    private fun searchMovie(view: View) {
        val text = textSearch.text
        if (text.isNotEmpty()) {
            view.hideKeyboard()
            openMovies(text.toString())
        } else
            showToast(EMPTY_TEXT_ERROR)
    }

    private fun openMovies(name: String) {
        val intent = intentFor<MoviesActivity>(
            LIST_OF_MOVIES to name
        )
        startActivity(intent)
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu, menu)
//        return super.onCreateOptionsMenu(menu)
//    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            R.id.citations -> {
//                val intent = intentFor<MainActivity>()
//                startActivity(intent)
//                true
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
//    }

}