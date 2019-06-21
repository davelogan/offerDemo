package com.dlogan.android.offers

import android.content.Context
import com.dlogan.android.offers.entity.Offer

interface OfferDetailsContract {

    interface View {
        fun showInfoMessage(msg: String)
        fun showOfferData(offer: Offer?)
        fun getContext(): Context
    }

    interface Presenter {
        // User actions
        fun backButtonClicked()

        fun favoriteCbClicked(offer: Offer?, favorite: Boolean)


        // Model updates
        fun onViewCreated(offerId: String?)

        fun onDestroy()
    }

    interface Interactor {
        fun loadOffer(offerId: String?)
        fun favoriteOffer(offer: Offer?, favorite: Boolean)
    }

    interface InteractorOutput {
        fun onFavoriteOfferSuccess(data: Offer)
        fun onFavoriteOfferError()
        fun onLoadOfferSuccess(data: Offer)
        fun onLoadOfferError()
    }
}