package com.dlogan.android.offers.presentor

import com.dlogan.android.offers.OffersDemoApplication
import com.dlogan.android.offers.OffersListContract
import com.dlogan.android.offers.data.Offer
import com.dlogan.android.offers.interactor.OfferListInteractor
import com.dlogan.android.offers.view.activities.OfferDetailActivity
import ru.terrakok.cicerone.Router


class OffersListPresenter(private var view: OffersListContract.View?) : OffersListContract.Presenter,
    OffersListContract.InteractorOutput {

    private var interactor: OffersListContract.Interactor? = OfferListInteractor(OffersDemoApplication.INSTANCE, this)
    private val router: Router? by lazy { OffersDemoApplication.INSTANCE.cicerone.router }

    override fun offerItemItemClicked(offer: Offer?) {
        router?.navigateTo(OfferDetailActivity.TAG, offer)
    }

    override fun onViewCreated() {
        view?.showLoading()
        interactor?.loadOffersList()
    }

    override fun onQuerySuccess(data: List<Offer>) {
        view?.hideLoading()
        view?.publishDataList(data)
    }

    override fun onQueryError() {
        view?.hideLoading()
        view?.showInfoMessage("Error when loading data")
    }

    override fun onDestroy() {
        view = null
        interactor = null
    }
}
