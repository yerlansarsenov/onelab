package kz.moviedb.data.mapper

import kz.moviedb.data.model.BaseListItemData
import kz.moviedb.domain.mapper.Mapper
import kz.moviedb.domain.model.BaseListItem

/**
 * Created by Sarsenov Yerlan on 27.01.2021.
 */
class SearchMapper : Mapper<BaseListItem.Search, BaseListItemData.SearchData> {

    override fun convert(model: BaseListItem.Search): BaseListItemData.SearchData {
        return BaseListItemData.SearchData(
            poster = model.poster,
            title = model.title,
            type = model.type,
            year = model.year,
            imdbID = model.imdbID
        )
    }

    override fun invert(model: BaseListItemData.SearchData): BaseListItem.Search {
        return BaseListItem.Search(
            poster = model.poster,
            title = model.title,
            type = model.type,
            year = model.year,
            imdbID = model.imdbID
        )
    }
}