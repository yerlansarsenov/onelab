package kz.moviedb.movieapp.moduls

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kz.moviedb.movieapp.api.ApiKeyInterceptor
import kz.moviedb.movieapp.api.MovieApi
import kz.moviedb.movieapp.api.wrok_manager.CitationApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Sarsenov Yerlan on 20.01.2021.
 */

const val MOVIES_URL = "http://www.omdbapi.com"
const val CITATION_URL = "https://api.forismatic.com/api/"

val retrofitCitationModule = module(override = true) {

    fun clientCitation() : OkHttpClient {
        val builder: OkHttpClient.Builder = OkHttpClient().newBuilder()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(interceptor)
        return builder.build()
    }

    fun retrofitCitation(client: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(CITATION_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
    }

    fun apiCitation(retrofit: Retrofit): CitationApi = retrofit.create(CitationApi::class.java)

    single { clientCitation() }
    single { retrofitCitation(get()) }
    single { apiCitation(get()) }
}

val retrofitMovieModule = module(override = true) {
    fun clientMovie() : OkHttpClient {
        val builder: OkHttpClient.Builder = OkHttpClient().newBuilder()
        val interceptor = ApiKeyInterceptor()
        builder.addInterceptor(interceptor)
        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(logInterceptor)
        return builder.build()
    }

    fun retrofitMovie(client: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(MOVIES_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    fun apiMovie(retrofit: Retrofit): MovieApi = retrofit.create(MovieApi::class.java)

    single { clientMovie() }
    single { retrofitMovie(get()) }
    single { apiMovie(get()) }
}


