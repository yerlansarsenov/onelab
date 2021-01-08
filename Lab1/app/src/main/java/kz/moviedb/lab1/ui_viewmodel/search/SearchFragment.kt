package kz.moviedb.lab1.ui_viewmodel.search

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import kz.moviedb.lab1.R
import kz.moviedb.lab1.lesson2_sandbox.hideKeyboard
import kz.moviedb.lab1.lesson2_sandbox.progressDialog
import kz.moviedb.lab1.lesson2_sandbox.showToast
import kz.moviedb.lab1.model.Search
import kz.moviedb.lab1.ui_viewmodel.ViewModelMainActivity
import kz.moviedb.lab1.ui_viewmodel.error.ERROR_TEXT
import kz.moviedb.lab1.ui_viewmodel.error.ErrorFragment
import kz.moviedb.lab1.ui_viewmodel.movies.LIST_OF_MOVIES
import kz.moviedb.lab1.ui_viewmodel.movies.MoviesFragment

/**
 * Created by Sarsenov Yerlan on 21.12.2020.
 */

const val EMPTY_TEXT_ERROR = "Empty text ERROR!"

class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModels()

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
        viewModel.liveDataSearchResponse.observe(viewLifecycleOwner) { response ->
            // from the server only 2 cases are comes: either Error or list of films
            if (response.Error != null && response.Error.isNotEmpty()) {
                openError(response.Error)
            } else if (response.Search.isNotEmpty()) {
                openMovies(response.Search)
            }
        }
        viewModel.liveDataLoading.observe(viewLifecycleOwner) {
            if (it) {
                showLoading()
            } else {
                hideLoading()
            }
        }
        viewModel.liveDataHasInternetProblems.observe(viewLifecycleOwner) {
            if (it) {
                openError("No access to internet")
            }
        }
        progressDialog = progressDialog()
        btnSearch.setOnClickListener {
            val text = textSearch.text
            if (text.isNotEmpty()) {
                view.hideKeyboard()
                openSecondFragment(text.toString())
            } else {
                showToast(EMPTY_TEXT_ERROR)
            }
        }
    }

    private fun openSecondFragment(text: String) {
        viewModel.searchMoviesByName(text)
    }

    fun showLoading() {
        Log.e(SearchFragment::javaClass.name, "showLoading: ")
        progressDialog?.show()
    }

    fun hideLoading() {
        Log.e(SearchFragment::javaClass.name, "hideLoading: ")
        progressDialog?.dismiss()
    }

    private fun openError(error: String) {
        val args = bundleOf(
            ERROR_TEXT to error
        )
        (activity as ViewModelMainActivity).replaceFragment(R.id.action_searchFragment_to_errorFragment2, args)
    }

    private fun openMovies(list: List<Search>) {
        val args = bundleOf(
            LIST_OF_MOVIES to list
        )
        (activity as ViewModelMainActivity).replaceFragment(R.id.action_searchFragment_to_moviesFragment2, args)
    }

    override fun onDetach() {
        progressDialog = null
        super.onDetach()
    }

}