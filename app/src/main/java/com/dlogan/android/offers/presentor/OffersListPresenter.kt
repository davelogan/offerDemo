package com.dlogan.android.offers.presentor

import com.dlogan.android.offers.OffersListContract
import com.dlogan.android.offers.Router
import com.dlogan.android.offers.entity.OfferHeader
import com.dlogan.android.offers.interactor.OfferListInteractor


class OffersListPresenter(private var view: OffersListContract.View?, private var router: Router) :
    OffersListContract.Presenter,
    OffersListContract.InteractorOutput {

    private var interactor: OffersListContract.Interactor? = OfferListInteractor(this)

    override fun offerItemItemClicked(offerId: String) {
        router.showOfferDetails(view?.getContext(), offerId)
    }

    override fun onViewCreated() {
        view?.showLoading()
        interactor?.loadOffersList()
    }

    override fun onLoadOffersListSuccess(data: List<OfferHeader>) {
        view?.hideLoading()
        view?.showOffers(data)
    }

    override fun onLoadOffersListError() {
        view?.hideLoading()
        view?.showInfoMessage("Error when loading data")
    }

    override fun onDestroy() {
        view = null
        interactor = null
    }
}
