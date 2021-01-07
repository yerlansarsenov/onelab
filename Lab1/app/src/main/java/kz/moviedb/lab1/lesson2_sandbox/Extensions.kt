package kz.moviedb.lab1.lesson2_sandbox

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.annotation.RequiresApi
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
 * Extension functions for Fragment, ImageView, RatingBar, Context etc.
 */

fun View.hideKeyboard() {
    val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
}

fun <T: Fragment> T.progressDialog() : AlertDialog? {
    val padding = 30
    var linearLayout : LinearLayout? = LinearLayout(activity)
    linearLayout?.orientation = LinearLayout.HORIZONTAL
    linearLayout?.setPadding(padding, padding, padding, padding)
    linearLayout?.gravity = Gravity.CENTER
    var llParam = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT)
    llParam.gravity = Gravity.CENTER
    linearLayout?.layoutParams = llParam

    var progressBar : ProgressBar? = ProgressBar(activity)
    progressBar?.isIndeterminate = true
    progressBar?.setPadding(0, 0, padding, 0)
    progressBar?.layoutParams = llParam

    llParam = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
    llParam.gravity = Gravity.CENTER

    var textView : TextView? = TextView(activity)
    textView?.text = "Loading..."
    textView?.setTextColor(Color.parseColor("#000000"))
    textView?.textSize = 20F
    textView?.layoutParams = llParam

    linearLayout?.addView(progressBar)
    linearLayout?.addView(textView)

    val builder = AlertDialog.Builder(activity)
    builder.setCancelable(false)
    builder.setView(linearLayout)

    return builder.create()
}

fun <T: Activity> T.progressDialog() : AlertDialog? {
    val padding = 30
    var linearLayout : LinearLayout? = LinearLayout(this)
    linearLayout?.orientation = LinearLayout.HORIZONTAL
    linearLayout?.setPadding(padding, padding, padding, padding)
    linearLayout?.gravity = Gravity.CENTER
    var llParam = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.WRAP_CONTENT,
        LinearLayout.LayoutParams.WRAP_CONTENT)
    llParam.gravity = Gravity.CENTER
    linearLayout?.layoutParams = llParam

    var progressBar : ProgressBar? = ProgressBar(this)
    progressBar?.isIndeterminate = true
    progressBar?.setPadding(0, 0, padding, 0)
    progressBar?.layoutParams = llParam

    llParam = LinearLayout.LayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT)
    llParam.gravity = Gravity.CENTER

    var textView : TextView? = TextView(this)
    textView?.text = "Loading..."
    textView?.setTextColor(Color.parseColor("#000000"))
    textView?.textSize = 20F
    textView?.layoutParams = llParam

    linearLayout?.addView(progressBar)
    linearLayout?.addView(textView)

    val builder = AlertDialog.Builder(this)
    builder.setCancelable(false)
    builder.setView(linearLayout)

    return builder.create()
}

fun <T: Fragment> T.showToast(text: String) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}

fun <T: Activity> T.showToast(text: String) {
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

@RequiresApi(Build.VERSION_CODES.M)
fun Context.checkPermission(permission: String) : Boolean {
    return this.checkSelfPermission(permission) == PERMISSION_GRANTED
}

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

/**
 * lazyArg for Fragments
 */

inline fun <reified T> Fragment.lazyArg(key: String) : Lazy<T> {
    return lazy(LazyThreadSafetyMode.NONE) {
        val value = requireArguments().get(key)
        return@lazy value as T
    }
}