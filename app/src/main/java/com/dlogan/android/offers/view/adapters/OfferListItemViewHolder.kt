package com.dlogan.android.offers.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dlogan.android.offers.R
import com.dlogan.android.offers.entity.OfferHeader
import kotlinx.android.synthetic.main.offer_item.view.*

/**
 * A simple ViewHolder that can bind a Offer item. It also accepts null items since the data may
 * not have been fetched before it is bound.
 */
class OfferListItemViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.offer_item, parent, false)
) {

    var offer: OfferHeader? = null

    fun bindTo(offer: OfferHeader?) {
        this.offer = offer
        itemView.currentValue.text = offer?.currentValue
        itemView.name.text = offer?.name
        //itemView.favorite.visibility = offer?.isFavorite ?: "gone"
        itemView.favorite.visibility = if (offer?.isFavorite == true) View.VISIBLE else View.GONE

        Glide.with(itemView)
            .load(offer?.url)
            .error(R.drawable.baseline_insert_photo_black_36)
            .into(itemView.imageView)
    }


}