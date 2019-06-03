package com.dlogan.android.offers

import com.dlogan.android.offers.entity.Offer

interface OfferDetailsContract {

    interface View {
        fun showInfoMessage(msg: String)
        fun showOfferData(offer: Offer?)
    }

    interface Presenter {
        // User actions
        fun backButtonClicked()
        fun favoriteCbClicked(offer: Offer?, favorite: Boolean)


        // Model updates
        fun onViewCreated(offer: Offer?)

        fun onDestroy()
    }

    interface Interactor {
        fun favoriteOffer(offer: Offer?, favorite: Boolean)
    }

    interface InteractorOutput {
        fun onSetAtFavoriteSuccess(data: Offer)
        fun onSetAtFavoriteError()
    }
}