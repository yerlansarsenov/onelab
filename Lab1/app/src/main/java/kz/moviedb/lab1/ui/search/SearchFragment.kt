package kz.moviedb.lab1.ui.search

import android.app.ProgressDialog
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
import kz.moviedb.lab1.lesson2_sandbox.showText
import kz.moviedb.lab1.model.Search
import kz.moviedb.lab1.ui.error.ERROR_TEXT
import kz.moviedb.lab1.ui.movies.LIST_OF_MOVIES
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

/**
 * Created by Sarsenov Yerlan on 21.12.2020.
 */

const val EMPTY_TEXT_ERROR = "Empty text ERROR!"

@Suppress("DEPRECATION")
class SearchFragment : MvpAppCompatFragment(), SearchView {

    lateinit var progressDialog: ProgressDialog

    private val presenter by moxyPresenter { SearchPresenter() }

    lateinit var navController: NavController

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        progressDialog = ProgressDialog(context)
        //progressDialog
    }

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
                presenter.searchMoviesByName(text.toString())
            } else {
                showText(EMPTY_TEXT_ERROR)
            }
        })
        /*btnSearch.setOnClickListener(View.OnClickListener {
            val text = textSearch.text
            if (text.isNotEmpty()) {
                //@TODO make request
                //Navigation.findNavController(view).navigate(R.id.action_searchFragment_to_errorFragment)
                showText(text = text.toString())
                val coroutineScope = CoroutineScope(Dispatchers.Main + Job())
                coroutineScope.launch {
//                    withContext(IO) {
//                        _liveData.postValue(ApiUtils.api().getMovieBySearch(text = text.toString()).await())
//                        try {
//                            Log.e("TAg", "onViewCreated: " + (liveData.value?.Search?.get(0)?.Title))
//                        } catch (e: NullPointerException) {
//                            Log.e("TAg", "onViewCreated: " + e.message)
//                        } catch (e: NetworkErrorException) {
//                            Log.e("TAg", "onViewCreated: " + e.message)
//                        } catch (e: Exception) {
//                            Log.e("TAg", "onViewCreated: " + e.message)
//                        }
//                    }
                }
                return@OnClickListener
            }
            showText(EMPTY_TEXT_ERROR)
        })*/
    }

    override fun showLoading() {
        Log.e(SearchFragment::javaClass.name, "showLoading: ")
        progressDialog.show()
    }

    override fun hideLoading() {
        Log.e(SearchFragment::javaClass.name, "hideLoading: ")
        progressDialog.hide()
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