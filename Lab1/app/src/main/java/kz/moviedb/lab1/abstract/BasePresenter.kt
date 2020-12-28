package kz.moviedb.lab1.abstract

import kotlinx.coroutines.*
import moxy.MvpPresenter
import moxy.presenterScope
import kotlin.coroutines.CoroutineContext

/**
 * Created by Sarsenov Yerlan on 24.12.2020.
 */
abstract class BasePresenter <P: BaseView> : MvpPresenter<P>() {


    fun <T> doAsyncWork(doAsync: suspend CoroutineScope.() -> T) {
        doCoroutineWork(doAsync, Dispatchers.IO)
    }

    private inline fun <T> doCoroutineWork(
        crossinline doAsync: suspend CoroutineScope.() -> T,
        context: CoroutineContext
    ) {
        presenterScope.launch {
            withContext(context) {
                viewState.showLoading()
                try {
                    doAsync.invoke(this)
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    viewState.hideLoading()
                }
            }
        }
    }
}