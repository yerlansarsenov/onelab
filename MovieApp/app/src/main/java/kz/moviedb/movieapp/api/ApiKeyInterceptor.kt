package kz.moviedb.movieapp.api

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * Created by Sarsenov Yerlan on 21.12.2020.
 */
class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request : Request = chain.request()
        val httpUrl: HttpUrl = request.url.newBuilder()
            .addQueryParameter("apikey", ApiKey.api_key)
            .build()
        request = request.newBuilder().url(httpUrl).build()
        return chain.proceed(request)
    }
}