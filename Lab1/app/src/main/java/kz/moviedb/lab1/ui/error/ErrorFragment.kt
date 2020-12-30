package kz.moviedb.lab1.ui.error

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import kz.moviedb.lab1.R
import kz.moviedb.lab1.lesson2_sandbox.showToast

/**
 * Created by Sarsenov Yerlan on 21.12.2020.
 */
const val ERROR_TEXT = "error_text"

class ErrorFragment : Fragment() {

    lateinit var errorText: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        errorText = if (arguments != null)
            requireArguments().getString(ERROR_TEXT, "")
        else ""
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fg_error, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val errorTextView = view.findViewById<TextView>(R.id.error_description)
        if (errorText.isNotEmpty()) {
            errorTextView.text = errorText
        } else {
            showToast("It is empty Or null")
        }
    }

}