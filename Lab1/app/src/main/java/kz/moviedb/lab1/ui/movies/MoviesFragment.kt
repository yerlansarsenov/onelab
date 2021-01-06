package kz.moviedb.lab1.ui.movies

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kz.moviedb.lab1.R
import kz.moviedb.lab1.lesson2_sandbox.progressDialog
import kz.moviedb.lab1.lesson2_sandbox.showToast
import kz.moviedb.lab1.model.MovieResponse
import kz.moviedb.lab1.model.Search
import kz.moviedb.lab1.ui.detail.MOVIE_KEY
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

/**
 * Created by Sarsenov Yerlan on 21.12.2020.
 */
const val LIST_OF_MOVIES = "list_of_movies"

@Suppress("DEPRECATION")
class MoviesFragment : MvpAppCompatFragment(), MoviesView, MoviesAdapter.OnClickListener {

    private val presenter by moxyPresenter { MoviesPresenter() }

    lateinit var listOfMovies: List<Search>

    var navController: NavController? = null

    lateinit var recyclerView: RecyclerView

    var adapter: MoviesAdapter? = null

    var progressDialog : AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            val list = requireArguments().get(LIST_OF_MOVIES)
            try {
                listOfMovies = list as List<Search>
            } catch (e: TypeCastException) {
                Log.e(MoviesFragment::javaClass.name, "onActivityCreated: ${e.message}")
            } catch (e: Exception) {
                Log.e(MoviesFragment::javaClass.name, "onActivityCreated: ${e.message}")
            }
        } else {
            showToast("It is null")
            listOfMovies = emptyList()
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fg_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        recyclerView = view.findViewById(R.id.recycler_view)
        progressDialog = progressDialog()
    }

    override fun onDestroyView() {
        navController = null
        recyclerView.adapter = null
        recyclerView.layoutManager = null
        super.onDestroyView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = MoviesAdapter(listOfMovies, this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))
    }

    override fun onDetach() {
        progressDialog = null
        super.onDetach()
    }

    override fun showLoading() {
        Log.e(MoviesFragment::javaClass.name, "showLoading: ")
        progressDialog?.show()
    }

    override fun hideLoading() {
        Log.e(MoviesFragment::javaClass.name, "hideLoading: ")
        progressDialog?.dismiss()
    }

    override fun showError(error: String) {
        showToast(error)
    }

    override fun openMovieDetail(movieResponse: MovieResponse) {
        Log.e(MoviesFragment::javaClass.name, "openMovieDetail: ")
        val args = bundleOf(
                MOVIE_KEY to movieResponse
        )
        navController?.navigate(R.id.action_movies_fg_to_detailFragment, args)
    }

    override fun listenOnItemClick(imdbId: String) {
        presenter.searchMovieById(imdbId)
    }

}