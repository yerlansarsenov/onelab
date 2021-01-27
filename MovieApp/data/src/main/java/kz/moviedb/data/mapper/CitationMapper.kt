package kz.moviedb.data.mapper

import kz.moviedb.data.model.BaseListItemData
import kz.moviedb.domain.mapper.Mapper
import kz.moviedb.domain.model.BaseListItem

/**
 * Created by Sarsenov Yerlan on 27.01.2021.
 */
class CitationMapper: Mapper<BaseListItem.RoomCitation, BaseListItemData.RoomCitationData> {
    override fun convert(model: BaseListItem.RoomCitation): BaseListItemData.RoomCitationData {
        return BaseListItemData.RoomCitationData(
            quoteText = model.quoteText,
            quoteAuthor = model.quoteAuthor,
            id = model.id
        )
    }

    override fun invert(model: BaseListItemData.RoomCitationData): BaseListItem.RoomCitation {
        return BaseListItem.RoomCitation(
            quoteAuthor = model.quoteAuthor,
            quoteText = model.quoteText,
            id = model.id
        )
    }
}