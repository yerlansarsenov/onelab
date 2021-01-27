package kz.moviedb.presentation.ui

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kz.moviedb.presentation.R
import kz.moviedb.presentation.ui.movies.MoviesActivity
import kz.moviedb.presentation.utils.showMatrixLoading

/**
 * Created by Sarsenov Yerlan on 07.01.2021.
 */
abstract class BaseActivity(resId: Int) : AppCompatActivity(resId) {

    var progressDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        progressDialog = showMatrixLoading()//showProcessLoading()
    }

    override fun onDestroy() {
        super.onDestroy()
        progressDialog = null
    }

    fun showLoading() {
        Log.e(MoviesActivity::javaClass.name, "showLoading: ")
        progressDialog?.show()
    }

    fun hideLoading() {
        Log.e(MoviesActivity::javaClass.name, "hideLoading: ")
        progressDialog?.dismiss()
    }

    fun showError(error: String) {
        setContentView(R.layout.ac_error)
        findViewById<TextView>(R.id.error_description).text = error
    }

}