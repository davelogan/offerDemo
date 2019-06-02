package com.dlogan.android.offers.presentor

import com.dlogan.android.offers.OffersListContract
import com.dlogan.android.offers.entity.Offer
import com.dlogan.android.offers.interactor.OfferListInteractor
import com.dlogan.android.offers.view.activities.OfferDetailActivity
import ru.terrakok.cicerone.Router
import javax.inject.Inject


class OffersListPresenter(private var view: OffersListContract.View?) : OffersListContract.Presenter,
    OffersListContract.InteractorOutput {

    private var interactor: OffersListContract.Interactor? = OfferListInteractor(this)

    @Inject
    lateinit var router: Router


    override fun offerItemItemClicked(offer: Offer?) {
        router.navigateTo(OfferDetailActivity.TAG, offer)
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
