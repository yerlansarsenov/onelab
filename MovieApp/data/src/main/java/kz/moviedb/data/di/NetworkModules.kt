package kz.moviedb.data.di

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kz.moviedb.data.*
import kz.moviedb.data.api.ApiKeyInterceptor
import kz.moviedb.data.api.MovieApi
import kz.moviedb.data.api.work_manager.CitationApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Sarsenov Yerlan on 20.01.2021.
 */

val retrofitCitationModule = module {

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

    single(named(CITATION_CLIENT)) { clientCitation() }
    single(named(CITATION_RETROFIT)) { retrofitCitation(get(named(CITATION_CLIENT))) }
    single { apiCitation(get(named(CITATION_RETROFIT))) }
}


val retrofitMovieModule = module {
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

    single(named(MOVIE_CLIENT)) { clientMovie() }
    single(named(MOVIE_RETROFIT)) { retrofitMovie(get(named(MOVIE_CLIENT))) }
    single { apiMovie(get(named(MOVIE_RETROFIT))) }
}


