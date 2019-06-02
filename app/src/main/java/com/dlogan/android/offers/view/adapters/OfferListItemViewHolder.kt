package com.dlogan.android.offers.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dlogan.android.offers.R
import com.dlogan.android.offers.data.Offer
import kotlinx.android.synthetic.main.offer_item.view.*

/**
 * A simple ViewHolder that can bind a Offer item. It also accepts null items since the data may
 * not have been fetched before it is bound.
 */
class OfferListItemViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.offer_item, parent, false)
) {

    private val currentValue = itemView.currentValue
    private val name = itemView.name
    private val imageView = itemView.imageView
    var offer: Offer? = null

    /**
     * Items might be null if they are not paged in yet. PagedListAdapter will re-bind the
     * ViewHolder when Item is loaded.
     */
    fun bindTo(offer: Offer?) {
        this.offer = offer
        currentValue.text = offer?.currentValue
        name.text = offer?.name

        Glide.with(itemView)
            .load(offer?.url)
            .into(this.imageView)
    }


}