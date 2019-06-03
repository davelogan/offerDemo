package com.dlogan.android.offers.presentor

import com.dlogan.android.offers.OfferDetailsContract
import com.dlogan.android.offers.OffersListContract
import com.dlogan.android.offers.entity.Offer
import com.dlogan.android.offers.interactor.OfferDetailsInteractor
import com.dlogan.android.offers.interactor.OfferListInteractor
import com.dlogan.android.offers.view.screens.OffersDetailScreen
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Inject


class OfferDetailsPresenter(private var view: OfferDetailsContract.View?, var cicerone: Cicerone<Router>) : OfferDetailsContract.Presenter {


    private var interactor: OfferDetailsContract.Interactor? = OfferDetailsInteractor()


    override fun onViewCreated(offer: Offer?) {
        view?.showOfferData(offer)
    }

    override fun backButtonClicked() {
        cicerone.router.exit()
    }

    override fun onDestroy() {
        view = null
        interactor = null
    }
}
