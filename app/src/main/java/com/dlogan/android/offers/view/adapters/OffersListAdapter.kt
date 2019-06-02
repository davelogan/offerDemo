package com.dlogan.android.offers.view.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dlogan.android.offers.data.Offer


class OffersListAdapter(private var listener: (Offer?) -> Unit, private var dataList: List<Offer>?) :
    RecyclerView.Adapter<OfferListItemViewHolder>() {

    override fun getItemCount() = dataList?.size ?: 0


    override fun onBindViewHolder(holderListItem: OfferListItemViewHolder, position: Int) {
        holderListItem.bindTo(getItem(position))
    }

    private fun getItem(position: Int): Offer? {
        return dataList?.get(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferListItemViewHolder =
        OfferListItemViewHolder(parent)

    fun updateData(list: List<Offer>) {
        this.dataList = list
        this.notifyDataSetChanged()
    }
}
