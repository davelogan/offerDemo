package com.dlogan.android.offers

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.dlogan.android.offers.data.Offer

/**
 * A simple PagedListAdapter that binds Offer items into CardViews.
 * <p>
 * PagedListAdapter is a RecyclerView.Adapter base class which can present the content of PagedLists
 * in a RecyclerView. It requests new pages as the user scrolls, and handles new PagedLists by
 * computing list differences on a background thread, and dispatching minimal, efficient updates to
 * the RecyclerView to ensure minimal UI thread work.
 * <p>
 * If you want to use your own Adapter base class, try using a PagedListAdapterHelper inside your
 * adapter instead.
 *
 * @see android.arch.paging.PagedListAdapter
 * @see android.arch.paging.AsyncPagedListDiffer
 */
class OfferAdapter : PagedListAdapter<Offer, OfferViewHolder>(diffCallback) {
    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder =
        OfferViewHolder(parent)

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Offer>() {
            override fun areItemsTheSame(oldItem: Offer, newItem: Offer): Boolean =
                oldItem.id == newItem.id

            /**
             * Note that in kotlin, == checking on data classes compares all contents, but in Java,
             * typically you'll implement Object#equals, and use it to compare object contents.
             */
            override fun areContentsTheSame(oldItem: Offer, newItem: Offer): Boolean =
                oldItem == newItem
        }
    }
}
