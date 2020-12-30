package kz.moviedb.lab1.lesson2_sandbox

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import java.io.Serializable
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

/**
 * AlertDialog
 */

class MyDialogBuilder(context: Context) {
    val builder = AlertDialog.Builder(context)

    fun title(text: Int) {
        builder.setTitle(text)
    }

    var description: String = ""
    set(value) {
        builder.setMessage(value)
    }

    fun positiveButton(text: Int) : PositiveButtonBuilder = PositiveButtonBuilder(text)

    /*infix fun Int.onClick(dialog: DialogInterface.() -> Unit) : Unit {
        builder.setPositiveButton(this) {
            it, _ -> it.dialog()
        }
    }*/

    infix fun PositiveButtonBuilder.onClick(dialog: DialogInterface.() -> Unit): Unit {
        builder.setPositiveButton(this.text) {
            it, _ -> it.dialog()
        }
    }

    class PositiveButtonBuilder(val text: Int) {

    }
}

inline fun AppCompatActivity.showDialog(builder: MyDialogBuilder.() -> Unit) : Unit{
    val myAlertDialogBuilder = MyDialogBuilder(this)
    myAlertDialogBuilder.builder()
    // myAlertDialogBuilder.builder.setMessage(myAlertDialogBuilder.description)
    myAlertDialogBuilder.builder.show()
}

/**
 *  StringBuilder
 */

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

/**
 *  Infix function
 */

infix fun <T> T.pairWith(other: T): Pair<T, T> {
    return Pair(this, other)
}

/**
 * Extension functions for Fragment, ImageView, RatingBar, Context
 */

fun <T: Fragment> T.showText(text: String) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}

fun <T: Activity> T.showText(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
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

fun Context.checkPermission(permission: String) : Boolean =
    this.checkCallingPermission(permission) == PERMISSION_GRANTED

/**
 * intentFor
 */

inline fun <reified T: AppCompatActivity> AppCompatActivity.intentFor(vararg pairs: Pair<String, Any?>): Intent {
    val intent = Intent(this, T::class.java)
    pairs.forEach {
        intent.putExtraOfAny(it.first, it.second)
    }
    return intent
}

fun Intent.putExtraOfAny(key: String, value: Any?) {
    when (value) {
        is Boolean -> putExtra(key, value)
        is Byte -> putExtra(key, value)
        is Char -> putExtra(key, value)
        is Double -> putExtra(key, value)
        is Float -> putExtra(key, value)
        is Int -> putExtra(key, value)
        is Long -> putExtra(key, value)
        is Short -> putExtra(key, value)
        is Bundle -> putExtra(key, value)
        is CharSequence -> putExtra(key, value)
        is Parcelable -> putExtra(key, value)
        is BooleanArray -> putExtra(key, value)
        is ByteArray -> putExtra(key, value)
        is CharArray -> putExtra(key, value)
        is DoubleArray -> putExtra(key, value)
        is FloatArray -> putExtra(key, value)
        is IntArray -> putExtra(key, value)
        is LongArray -> putExtra(key, value)
        is ShortArray -> putExtra(key, value)
        is Serializable -> putExtra(key, value)
    }
}

/**
 *  lazyArg
 *
 */

inline fun <reified T> Activity.lazyArg(key: String) : Lazy<T> {
    return lazy(LazyThreadSafetyMode.NONE) {
        val value = intent.extras?.get(key)
        return@lazy value as T
    }
}