package kz.moviedb.presentation.di

import kz.moviedb.presentation.model.RatingMapperUI
import org.koin.dsl.module

/**
 * Created by Sarsenov Yerlan on 28.01.2021.
 */

val mapperModuleUI = module {
    factory { RatingMapperUI() }
}