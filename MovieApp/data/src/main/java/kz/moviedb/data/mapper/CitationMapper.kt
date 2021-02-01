package kz.moviedb.data.mapper

import kz.moviedb.data.model.BaseListItemData
import kz.moviedb.domain.mapper.Mapper
import kz.moviedb.domain.model.BaseListItem

/**
 * Created by Sarsenov Yerlan on 27.01.2021.
 */
class CitationMapper: Mapper<BaseListItemData.RoomCitationData, BaseListItem.RoomCitation> {

    override fun convert(model: BaseListItemData.RoomCitationData): BaseListItem.RoomCitation {
        return BaseListItem.RoomCitation(
            quoteAuthor = model.quoteAuthor,
            quoteText = model.quoteText,
            id = model.id
        )
    }
}