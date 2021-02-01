package kz.moviedb.presentation.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kz.moviedb.domain.model.Rating
import kz.moviedb.presentation.R
import kz.moviedb.presentation.utils.CustomRatingView

/**
 * Created by Sarsenov Yerlan on 28.01.2021.
 */
class RatingAdapter : ListAdapter<Rating, RatingViewHolder>(RatingDiffUtils()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.it_rating, parent, false)
        return RatingViewHolder(view)
    }

    override fun onBindViewHolder(holder: RatingViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

}

class RatingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(item: Rating) {
//        itemView.findViewById<TextView>(R.id.view_rating).text = item.value.toString()
        itemView.findViewById<CustomRatingView>(R.id.view_rating).rate = item.value
        itemView.findViewById<TextView>(R.id.name_rating).text = item.source
    }
}

class RatingDiffUtils: DiffUtil.ItemCallback<Rating>() {
    override fun areItemsTheSame(oldItem: Rating, newItem: Rating): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Rating, newItem: Rating): Boolean {
        return oldItem.source == newItem.source
    }

}