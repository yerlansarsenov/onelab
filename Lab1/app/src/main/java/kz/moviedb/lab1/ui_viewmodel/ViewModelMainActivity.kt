package kz.moviedb.lab1.ui_viewmodel

import android.os.Bundle
import android.util.Log
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

    lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        navController = Navigation.findNavController(this, R.id.container)
    }

//    inline fun <reified T: Fragment> replaceFragment(clazz: Class<T>, args: Bundle?) {
//        val addToBackStack = supportFragmentManager.findFragmentById(R.id.container) != null
//        val tr = supportFragmentManager.beginTransaction()
//            .replace(R.id.container, clazz, args)
//        if (addToBackStack)
//            tr.addToBackStack(null)
//        tr.commit()
//    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp() || navController.navigateUp()
    }

    fun replaceFragment(res: Int, args: Bundle?) {
        navController.navigate(res, args)
    }

    override fun onBackPressed() {
        navController.popBackStack()
    }

}