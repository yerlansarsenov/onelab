package kz.moviedb.movieapp.ui

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kz.moviedb.movieapp.ui.movies.MoviesActivity
import kz.moviedb.movieapp.utils.progressDialog

/**
 * Created by Sarsenov Yerlan on 07.01.2021.
 */
abstract class BaseActivity(resId: Int) : AppCompatActivity(resId) {

    var progressDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        progressDialog = progressDialog()
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

}