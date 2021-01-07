package kz.moviedb.lab1.ui_viewmodel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kz.moviedb.lab1.R
import kz.moviedb.lab1.ui_viewmodel.search.SearchFragment

/**
 * Created by Sarsenov Yerlan on 06.01.2021.
 */
class ViewModelMainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        if (savedInstanceState == null)
            replaceFragment(SearchFragment::class.java, null)
    }

    inline fun <reified T: Fragment> replaceFragment(clazz: Class<T>, args: Bundle?) {
        val addToBackStack = supportFragmentManager.findFragmentById(R.id.container) != null
        val tr = supportFragmentManager.beginTransaction()
            .replace(R.id.container, clazz, args)
        if (addToBackStack)
            tr.addToBackStack(clazz.simpleName)
        tr.commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount != 0) {
            supportFragmentManager.popBackStackImmediate()
        } else {
            super.onBackPressed()
        }
    }
}