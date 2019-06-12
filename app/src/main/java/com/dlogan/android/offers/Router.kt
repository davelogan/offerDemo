package com.dlogan.android.offers

import android.app.Activity
import android.content.Context
import com.dlogan.android.offers.view.activities.OfferDetailActivity
import com.dlogan.android.offers.view.activities.OffersListActivity


class Router {

    fun showOffersList(context: Context?) {
        context?.let {
            val intent = OffersListActivity.callingIntent(context)
            context.startActivity(intent)
        }
    }

    fun showOfferDetails(context: Context?, offerId: String?) {
        context?.let {
            val intent = OfferDetailActivity.callingIntent(context, offerId)
            context.startActivity(intent)
        }
    }

    fun exit(activity: Activity?) {
        activity?.finish()
    }
}