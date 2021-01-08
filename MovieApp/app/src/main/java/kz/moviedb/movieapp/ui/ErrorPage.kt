package kz.moviedb.movieapp.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kz.moviedb.movieapp.R
import kz.moviedb.movieapp.utils.lazyArg

/**
 * Created by Sarsenov Yerlan on 07.01.2021.
 */

const val ERROR_TEXT = "error_text"

class ErrorPage : AppCompatActivity(R.layout.ac_error) {

    private val errorText by lazyArg<String>(ERROR_TEXT)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val errorTextView = findViewById<TextView>(R.id.error_description)
        errorTextView.text = errorText
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}