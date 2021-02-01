package kz.moviedb.data.mapper

import kz.moviedb.data.model.BaseListItemData
import kz.moviedb.domain.mapper.Mapper
import kz.moviedb.domain.model.BaseListItem

/**
 * Created by Sarsenov Yerlan on 27.01.2021.
 */
class SearchMapper : Mapper<BaseListItemData.SearchData, BaseListItem.Search> {

    override fun convert(model: BaseListItemData.SearchData): BaseListItem.Search {
        return BaseListItem.Search(
            poster = model.poster,
            title = model.title,
            imdbID = model.imdbID
        )
    }
}