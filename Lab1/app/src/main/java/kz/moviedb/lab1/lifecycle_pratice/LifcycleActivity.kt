package kz.moviedb.lab1.lifecycle_pratice

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import kz.moviedb.lab1.R
import kz.moviedb.lab1.lesson2_sandbox.showToast

/**
 * Created by Sarsenov Yerlan on 06.01.2021.
 */

const val SOME_VALUE = "random text"

class LifcycleActivity : AppCompatActivity() {

    private val viewModel: LifecycleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showToast("here")
        setContentView(R.layout.activity_lifecycle)
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_1, LifecycleFragment1())
                .commit()
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_2, LifecycleFragment2())
                .commit()
        viewModel.liveData.value = SOME_VALUE
    }

}