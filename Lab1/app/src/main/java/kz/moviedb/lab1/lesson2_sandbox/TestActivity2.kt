package kz.moviedb.lab1.lesson2_sandbox

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kz.moviedb.lab1.R
import kz.moviedb.lab1.ui.MainActivity

const val EXTRA_1 = "EXTRA_1"
const val EXTRA_2 = "EXTRA_2"

class TestActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test2)
        val intent = intentFor<MainActivity>(
                EXTRA_1 to "value1",
                EXTRA_2 to 2
        )
        showDialog {
            title(R.string.app_name)
            description = "this is description!"
            Log.e(TestActivity2::javaClass.name, "onCreate: ")
            positiveButton(R.string.app_name) onClick {
                startActivity(intent)
                this.dismiss()
            }
        }
        val hasInternetPermission = (this as Context).checkPermission(android.Manifest.permission.INTERNET)
        if (hasInternetPermission) {
            showToast("has internet permission")
        } else {
            showToast("has not internet permission")
        }
    }
}