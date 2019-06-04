package com.dlogan.android.offers.view.screens

import android.content.Context
import android.content.Intent
import com.dlogan.android.offers.view.activities.OffersListActivity
import ru.terrakok.cicerone.android.support.SupportAppScreen

class OffersListScreen : SupportAppScreen() {

    override fun getActivityIntent(context: Context?): Intent {
        return Intent(context, OffersListActivity::class.java)
    }
}