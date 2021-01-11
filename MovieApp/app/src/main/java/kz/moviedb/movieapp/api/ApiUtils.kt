package kz.moviedb.movieapp.api

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kz.moviedb.movieapp.BuildConfig
import kz.moviedb.movieapp.api.wrok_manager.CitationApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

/**
 * Created by Sarsenov Yerlan on 21.12.2020.
 */
object ApiUtils {
    private fun client_Movie() : OkHttpClient {
        val builder: OkHttpClient.Builder = OkHttpClient().newBuilder()
        val interceptor = ApiKeyInterceptor()
        builder.addInterceptor(interceptor)
        val log_interceptor = HttpLoggingInterceptor()
        log_interceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(log_interceptor)
        return builder.build()
    }

    private fun retrofit_Movie() : Retrofit {
        val gson = Gson()
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.MOVIES_URL)
            .client(client_Movie())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
        return retrofit
    }

    private fun  client_Citation() : OkHttpClient {
        val builder: OkHttpClient.Builder = OkHttpClient().newBuilder()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(interceptor)
        return builder.build()
    }

    private fun retrofit_Citation() : Retrofit {
        //val gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder()
            .baseUrl(BuildConfig.CITATION_URL)
            .client(client_Citation())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
    }

    fun api_Citation(): CitationApi = retrofit_Citation().create()

    fun api_Movie(): MovieAPI = retrofit_Movie().create()
}