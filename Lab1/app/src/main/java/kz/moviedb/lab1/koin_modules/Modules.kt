package kz.moviedb.lab1.koin_modules

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kz.moviedb.lab1.BuildConfig
import kz.moviedb.lab1.api.ApiKeyInterceptor
import kz.moviedb.lab1.api.MovieAPI
import kz.moviedb.lab1.repository.MovieRepository
import kz.moviedb.lab1.repository.MovieRepositoryImpl
import kz.moviedb.lab1.ui.movies.MoviesPresenter
import kz.moviedb.lab1.ui.search.SearchPresenter
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Sarsenov Yerlan on 14.01.2021.
 */

val retrofitModule = module {
    fun provideHttpClient(): OkHttpClient {
        val builder: OkHttpClient.Builder = OkHttpClient().newBuilder()
        val interceptor = ApiKeyInterceptor()
        builder.addInterceptor(interceptor)
        val log_interceptor = HttpLoggingInterceptor()
        log_interceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(log_interceptor)
        return builder.build()
    }

    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.MOVIES_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    single { provideHttpClient() }
    single { provideRetrofit(get()) }
}

val apiModule = module {
    fun provideApi(retrofit: Retrofit): MovieAPI {
        return retrofit.create(MovieAPI::class.java)
    }
    single { provideApi(get()) }
}

val repositoryModule = module {
    single { MovieRepositoryImpl(get()) }
}

val presenterModule = module {
    single { SearchPresenter(get()) }
    single { MoviesPresenter(get()) }
}

//val applicationModules = module {
//    factory<SearchPresenter> {
//        SearchPresenter(get())
//    }
//    factory<MoviesPresenter> {
//        MoviesPresenter(get())
//    }
//}