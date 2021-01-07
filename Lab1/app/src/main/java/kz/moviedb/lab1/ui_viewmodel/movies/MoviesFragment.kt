package kz.moviedb.lab1.ui_viewmodel.movies

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kz.moviedb.lab1.R
import kz.moviedb.lab1.lesson2_sandbox.lazyArg
import kz.moviedb.lab1.lesson2_sandbox.progressDialog
import kz.moviedb.lab1.lesson2_sandbox.showToast
import kz.moviedb.lab1.model.MovieResponse
import kz.moviedb.lab1.model.Search
import kz.moviedb.lab1.ui.detail.MOVIE_KEY
import kz.moviedb.lab1.ui_viewmodel.ViewModelMainActivity
import kz.moviedb.lab1.ui_viewmodel.detail.DetailFragment

/**
 * Created by Sarsenov Yerlan on 21.12.2020.
 */
const val LIST_OF_MOVIES = "list_of_movies"

class MoviesFragment : Fragment(), MoviesAdapter.OnClickListener {

    private val listOfMovies by lazyArg<List<Search>>(LIST_OF_MOVIES)

    lateinit var recyclerView: RecyclerView

    var adapter: MoviesAdapter? = null

    var progressDialog: AlertDialog? = null

    private val viewModel by viewModels<MoviesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fg_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recycler_view)
        progressDialog = progressDialog()
        viewModel.liveDataHasInternetProblems.observe(viewLifecycleOwner) {
            if (it) {
                showError("Some problems with internet")
            }
        }
        viewModel.liveDataLoading.observe(viewLifecycleOwner) {
            if (it) {
                showLoading()
            } else {
                hideLoading()
            }
        }
        viewModel.liveDataMovie.observe(viewLifecycleOwner) { response ->
            if (response.Error !== null && response.Error.isNotEmpty()) {
                showError(response.Error)
            } else {
                openMovieDetail(response)
            }
        }
    }

    override fun onDestroyView() {
        recyclerView.adapter = null
        recyclerView.layoutManager = null
        super.onDestroyView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = MoviesAdapter(listOfMovies, this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                activity,
                LinearLayoutManager.VERTICAL
            )
        )
    }

    override fun onDetach() {
        progressDialog = null
        super.onDetach()
    }

    fun showLoading() {
        Log.e(MoviesFragment::javaClass.name, "showLoading: ")
        progressDialog?.show()
    }

    fun hideLoading() {
        Log.e(MoviesFragment::javaClass.name, "hideLoading: ")
        progressDialog?.dismiss()
    }

    private fun showError(error: String) {
        showToast(error)
    }

    override fun listenOnItemClick(imdbId: String) {
        viewModel.searchMovieById(imdbId)
    }

    private fun openMovieDetail(movieResponse: MovieResponse) {
        val args = bundleOf(
            MOVIE_KEY to movieResponse
        )
        (activity as ViewModelMainActivity).replaceFragment(DetailFragment::class.java, args)
    }

}