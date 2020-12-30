package kz.moviedb.lab1.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kz.moviedb.lab1.R
import kz.moviedb.lab1.lesson2_sandbox.*

class MainActivity : AppCompatActivity() {

    lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.nav_fragment)
//        val per = (this as Context).checkPermission(android.Manifest.permission.INTERNET) // per = true
//        showDialog {
//            title(R.string.app_name)
//            description = "this is description!"
//            Log.e(MainActivity::javaClass.name, "onCreate: ")
//            positiveButton(R.string.app_name) onClick {
//                this.dismiss()
//            }
//        }
        val extra1 : String by lazyArg(EXTRA_1)
        val extra2 : Int by lazyArg(EXTRA_2)
        showText("extra1 = $extra1 \n extra2 = $extra2")
    }



    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp() || navController.navigateUp()
    }
}