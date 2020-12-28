package kz.moviedb.lab1.lesson2_sandbox

import android.util.Log
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import java.lang.StringBuilder

/**
 * Created by Sarsenov Yerlan on 18.12.2020.
 */

class MyClass {
    companion object {
        @JvmStatic
        fun main(vararg args: String): Unit {
            val myString = createString {
                val q = 456
                append("asd")
                appendLn("asdsf")
                appendLn("q = $q")
            }
            print(myString)
            mapOf(
                    "asd" pairWith "qwe"
            )
        }
    }
}

class MyStringBuilder {
    val result = StringBuilder()
    fun append(text: String) {
        result.append(text)
    }

    fun appendLn(text: String) {
        result.appendLine(text)
    }
}

inline fun <reified T> T.createString(builder: MyStringBuilder.() -> Unit) : String {
    val myStringBuilder = MyStringBuilder()
    myStringBuilder.append(T::class.java.simpleName + " ")
    myStringBuilder.builder()
    return myStringBuilder.result.toString()
}

infix fun <T> T.pairWith(other: T): Pair<T, T> {
    return Pair(this, other)
}

inline fun inlinedFunc() {

}

class MyAlertDialogBuilder {
    //val title: String()

}

fun Fragment.showText(text: String) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}

fun ImageView.setImageWithUrl(url: String) {
    Picasso.get().load(url).into(this)
}

fun RatingBar.setImdbRating(rate: String) {
    try {
        val rateFloat = rate.toFloat()
        this.rating = rateFloat/2f
    } catch (e: Exception) {
        val TAG = "RB_Exten_fun"
        Log.e(TAG, "setImdbRating: ${e.message}")
    }
}
