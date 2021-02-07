package kz.moviedb.presentation.model

import kz.moviedb.domain.model.BaseListItem

/**
 * Created by Sarsenov Yerlan on 27.01.2021.
 */

sealed class SearchState {
    data class ResponseList(val list: List<BaseListItem>): SearchState()
    data class Error(val message: String): SearchState()
}

