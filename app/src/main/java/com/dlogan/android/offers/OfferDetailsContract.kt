package com.dlogan.android.offers

import com.dlogan.android.offers.entity.Offer

interface OfferDetailsContract {

    interface View {
        fun showInfoMessage(msg: String)
    }

    interface Presenter {
        // User actions
        fun backButtonClicked()

        // Model updates
        fun onViewCreated(offer: Offer?)

        fun onDestroy()
    }

    interface Interactor {
        fun favoriteOffer(offer: Offer, isFavorite: Boolean)
    }
}