package kz.moviedb.lab1.lifecycle_pratice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import kz.moviedb.lab1.R
import kz.moviedb.lab1.lesson2_sandbox.showToast

/**
 * Created by Sarsenov Yerlan on 06.01.2021.
 */
class LifecycleFragment1 : Fragment() {

    private val activityViewModel by activityViewModels<LifecycleViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fg_lifecycle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textView = view.findViewById<TextView>(R.id.text_view)
        activityViewModel.liveData.observe(viewLifecycleOwner) {
            showToast("$it fragment1")
            textView.text = it
        }
    }

}