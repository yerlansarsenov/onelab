package kz.moviedb.presentation.ui.movies

import androidx.recyclerview.widget.DiffUtil
import kz.moviedb.domain.model.BaseListItem

class SearchItemCallBack : DiffUtil.ItemCallback<BaseListItem>() {
    override fun areItemsTheSame(oldItem: BaseListItem, newItem: BaseListItem): Boolean {
        if (oldItem is BaseListItem.Search && newItem is BaseListItem.Search) {
            return oldItem.imdbID == newItem.imdbID
        }
        if (oldItem is BaseListItem.RoomCitation && newItem is BaseListItem.RoomCitation) {
            return oldItem.id == newItem.id
        }
        return false
    }

    override fun areContentsTheSame(oldItem: BaseListItem, newItem: BaseListItem): Boolean {
        if (oldItem is BaseListItem.Search && newItem is BaseListItem.Search) {
            return oldItem.title == newItem.title
        }
        if (oldItem is BaseListItem.RoomCitation && newItem is BaseListItem.RoomCitation) {
            return oldItem.quoteText == newItem.quoteText || oldItem.quoteAuthor == newItem.quoteAuthor
        }
        return false
    }

}