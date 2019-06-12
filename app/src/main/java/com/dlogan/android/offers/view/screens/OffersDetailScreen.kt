package com.dlogan.android.offers.view.screens

import android.content.Context
import android.content.Intent
import com.dlogan.android.offers.utilities.OFFER_KEY
import com.dlogan.android.offers.view.activities.OfferDetailActivity
import ru.terrakok.cicerone.android.support.SupportAppScreen

class OffersDetailScreen constructor(private val offerId: String?) : SupportAppScreen() {

    override fun getActivityIntent(context: Context?): Intent {
        val intent = Intent(context, OfferDetailActivity::class.java)
        intent.putExtra(OFFER_KEY, offerId)
        return intent
    }

}