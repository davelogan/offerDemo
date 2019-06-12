package com.dlogan.android.offers.presentor

import android.app.Activity
import com.dlogan.android.offers.OfferDetailsContract
import com.dlogan.android.offers.Router
import com.dlogan.android.offers.entity.Offer
import com.dlogan.android.offers.interactor.OfferDetailsInteractor


class OfferDetailsPresenter(private var view: OfferDetailsContract.View?, var router: Router) :
    OfferDetailsContract.Presenter, OfferDetailsContract.InteractorOutput {

    private var interactor: OfferDetailsContract.Interactor? = OfferDetailsInteractor(this)

    override fun onViewCreated(offerId: String?) {
        interactor?.loadOffer(offerId)
    }

    override fun backButtonClicked() {
        router.exit(view?.getContext() as Activity)
    }

    override fun favoriteCbClicked(offer: Offer?, favorite: Boolean) {
        interactor?.favoriteOffer(offer, favorite)
    }


    override fun onDestroy() {
        view = null
        interactor = null
    }

    override fun onFavoriteOfferSuccess(data: Offer) {
        view?.showOfferData(data)
    }

    override fun onFavoriteOfferError() {
        view?.showInfoMessage("Error when updating Offer")
    }

    override fun onLoadOfferSuccess(data: Offer) {
        view?.showOfferData(data)
    }

    override fun onLoadOfferError() {
        view?.showInfoMessage("Error when loading Offer")
    }
}
