package kz.moviedb.lab1.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleObserver
import kz.moviedb.lab1.R

/**
 * Created by Sarsenov Yerlan on 06.01.2021.
 */

const val SOME_VALUE = "random text"

class LifcycleActivity : AppCompatActivity() {

    private val viewModel: LifecycleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_lifecycle)
        supportFragmentManager.beginTransaction()
                .add(R.id.fragment_1, LifecycleFragment1())
                .commit()
        supportFragmentManager.beginTransaction()
                .add(R.id.fragment_2, LifecycleFragment2())
                .commit()
        viewModel.liveData.value = SOME_VALUE
    }

}