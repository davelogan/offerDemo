package com.dlogan.android.offers.view.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dlogan.android.offers.entity.OfferHeader


class OffersListAdapter(private var listener: (String?) -> Unit, private var dataList: List<OfferHeader>?) :
    RecyclerView.Adapter<OfferListItemViewHolder>() {

    override fun getItemCount() = dataList?.size ?: 0


    override fun onBindViewHolder(holderListItem: OfferListItemViewHolder, position: Int) {
        holderListItem.bindTo(getItem(position))
        holderListItem.itemView.setOnClickListener { listener(dataList?.get(position)?.id) }
    }

    private fun getItem(position: Int): OfferHeader? {
        return dataList?.get(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferListItemViewHolder =
        OfferListItemViewHolder(parent)

    fun updateData(list: List<OfferHeader>) {
        this.dataList = list
        this.notifyDataSetChanged()
    }
}
