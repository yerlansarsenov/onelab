package kz.moviedb.lab1.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import kz.moviedb.lab1.R
import kz.moviedb.lab1.lesson2_sandbox.setImageWithUrl
import kz.moviedb.lab1.lesson2_sandbox.setImdbRating
import kz.moviedb.lab1.model.MovieResponse
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

/**
 * Created by Sarsenov Yerlan on 21.12.2020.
 */
const val MOVIE_KEY = "movie_key"

class DetailFragment : MvpAppCompatFragment(), DetailView {

    private val presenter by moxyPresenter { DetailPresenter() }

    var movie: MovieResponse? = null

    lateinit var imageView: ImageView
    lateinit var titleView: TextView
    lateinit var rateBar: RatingBar
    lateinit var releaseView: TextView
    lateinit var writersView: TextView
    lateinit var actorsView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            val movie = requireArguments().get(MOVIE_KEY)
            try {
                this.movie = movie as MovieResponse
            } catch (e: Exception) {
                Log.e(DetailFragment::javaClass.name, "onCreate: ${e.message}")
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fg_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        presenter.decorateView()
    }

    private fun initView(view: View) {
        imageView = view.findViewById(R.id.image_detail)
        rateBar = view.findViewById(R.id.rate_view_detail)
        titleView = view.findViewById(R.id.title_view_detail)
        releaseView = view.findViewById(R.id.relies_view_detail)
        writersView = view.findViewById(R.id.writers_tv)
        actorsView = view.findViewById(R.id.actors_tv)
    }

    override fun decorateViews() {
        if (movie == null)
            return
        imageView.setImageWithUrl(movie!!.Poster)
        rateBar.setImdbRating(movie!!.imdbRating)
        titleView.text = movie!!.Title
        releaseView.text = movie!!.Released
        writersView.text = movie!!.Writer
        actorsView.text = movie!!.Actors
    }

}