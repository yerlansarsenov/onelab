package kz.moviedb.movieapp.api

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kz.moviedb.movieapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Sarsenov Yerlan on 21.12.2020.
 */
object ApiUtils {
    private fun client() : OkHttpClient {
        val builder: OkHttpClient.Builder = OkHttpClient().newBuilder()
        val interceptor = ApiKeyInterceptor()
        builder.addInterceptor(interceptor)
        val log_interceptor = HttpLoggingInterceptor()
        log_interceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(log_interceptor)
        return builder.build()
    }

    private fun retrofit() : Retrofit {
        val gson = Gson()
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.MOVIES_URL)
            .client(client())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
        return retrofit
    }

    fun api(): MovieAPI = retrofit().create(MovieAPI::class.java)
}