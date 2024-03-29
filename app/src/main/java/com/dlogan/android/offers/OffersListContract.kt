package com.dlogan.android.offers

import android.content.Context
import com.dlogan.android.offers.entity.OfferHeader

interface OffersListContract {
    interface View {
        fun showLoading()
        fun hideLoading()
        fun showOffers(data: List<OfferHeader>)
        fun showInfoMessage(msg: String)
        fun getContext(): Context
    }

    interface Presenter {
        // User actions
        fun offerItemItemClicked(offerId: String)

        // Model updates
        fun onViewCreated()

        fun onDestroy()
    }

    interface Interactor {
        fun loadOffersList()
    }

    interface InteractorOutput {
        fun onLoadOffersListSuccess(data: List<OfferHeader>)
        fun onLoadOffersListError()
    }
}
