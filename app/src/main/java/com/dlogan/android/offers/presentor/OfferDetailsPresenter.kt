package com.dlogan.android.offers.presentor

import com.dlogan.android.offers.OfferDetailsContract
import com.dlogan.android.offers.OffersListContract
import com.dlogan.android.offers.entity.Offer
import com.dlogan.android.offers.interactor.OfferDetailsInteractor
import com.dlogan.android.offers.interactor.OfferListInteractor
import com.dlogan.android.offers.view.screens.OffersDetailScreen
import ru.terrakok.cicerone.Router
import javax.inject.Inject


class OfferDetailsPresenter(private var view: OfferDetailsContract.View?) : OfferDetailsContract.Presenter {

    override fun backButtonClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private var interactor: OfferDetailsContract.Interactor? = OfferDetailsInteractor()

    @Inject
    lateinit var router: Router

    override fun onViewCreated(offer: Offer?) {
        view?.showOfferData(offer)
    }

    override fun onDestroy() {
        view = null
        interactor = null
    }
}
