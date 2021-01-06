package kz.moviedb.lab1.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import kz.moviedb.lab1.R

/**
 * Created by Sarsenov Yerlan on 06.01.2021.
 */
class LifecycleFragment2 : Fragment() {

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
        activityViewModel.liveData.observe(viewLifecycleOwner) {
            val textView = view.findViewById<TextView>(R.id.textview)
            textView.text = it
        }
    }

}