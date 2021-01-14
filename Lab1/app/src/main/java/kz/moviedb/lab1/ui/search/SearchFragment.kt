package kz.moviedb.lab1.ui.search

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kz.moviedb.lab1.R
import kz.moviedb.lab1.lesson2_sandbox.hideKeyboard
import kz.moviedb.lab1.lesson2_sandbox.progressDialog
import kz.moviedb.lab1.lesson2_sandbox.showToast
import kz.moviedb.lab1.model.Search
import kz.moviedb.lab1.ui.error.ERROR_TEXT
import kz.moviedb.lab1.ui.movies.LIST_OF_MOVIES
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import org.koin.android.ext.android.get

/**
 * Created by Sarsenov Yerlan on 21.12.2020.
 */

const val EMPTY_TEXT_ERROR = "Empty text ERROR!"

class SearchFragment : MvpAppCompatFragment(), SearchView {

    @InjectPresenter
    lateinit var presenter: SearchPresenter

    @ProvidePresenter
    fun provide(): SearchPresenter = get()

    lateinit var navController: NavController

    var progressDialog: AlertDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fg_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnSearch = view.findViewById<Button>(R.id.search_btn)
        val textSearch = view.findViewById<EditText>(R.id.search_text)
        navController = Navigation.findNavController(view)
        btnSearch.setOnClickListener(View.OnClickListener {
            val text = textSearch.text
            if (text.isNotEmpty()) {
                view.hideKeyboard()
                presenter.searchMoviesByName(text.toString())
            } else {
                showToast(EMPTY_TEXT_ERROR)
            }
        })
        progressDialog = progressDialog()
    }

    override fun onDetach() {
        progressDialog = null
        super.onDetach()
    }

    override fun showLoading() {
        Log.e(SearchFragment::javaClass.name, "showLoading: ")
        progressDialog?.show()
    }

    override fun hideLoading() {
        Log.e(SearchFragment::javaClass.name, "hideLoading: ")
        progressDialog?.dismiss()
    }

    override fun openMovies(list: List<Search>) {
        val args = bundleOf(
            LIST_OF_MOVIES to list
        )
        navController.navigate(R.id.action_searchFragment_to_moviesFragment, args)
    }

    override fun openError(error: String) {
        Log.e(SearchFragment::javaClass.name, "openError: $error")
        val args = bundleOf(
            ERROR_TEXT to error
        )
        navController.navigate(R.id.action_searchFragment_to_errorFragment, args)
    }


}