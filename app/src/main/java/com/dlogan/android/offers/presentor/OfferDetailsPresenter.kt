package com.dlogan.android.offers.presentor

import com.dlogan.android.offers.OfferDetailsContract
import com.dlogan.android.offers.entity.Offer
import com.dlogan.android.offers.interactor.OfferDetailsInteractor
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router


class OfferDetailsPresenter(private var view: OfferDetailsContract.View?, var cicerone: Cicerone<Router>) :
    OfferDetailsContract.Presenter, OfferDetailsContract.InteractorOutput {


    private var interactor: OfferDetailsContract.Interactor? = OfferDetailsInteractor(this)

    override fun onViewCreated(offer: Offer?) {
        view?.showOfferData(offer)
    }

    override fun backButtonClicked() {
        cicerone.router.exit()
    }

    override fun favoriteCbClicked(offer: Offer?, favorite: Boolean) {
        interactor?.favoriteOffer(offer, favorite)
    }


    override fun onDestroy() {
        view = null
        interactor = null
    }

    override fun onSetAtFavoriteSuccess(data: Offer) {
        view?.showOfferData(data)
    }

    override fun onSetAtFavoriteError() {
        view?.showInfoMessage("Error when updating Offer")
    }
}
