package com.dlogan.android.offers

import com.dlogan.android.offers.entity.Offer

interface OffersListContract {
    interface View {
        fun showLoading()
        fun hideLoading()
        fun publishDataList(data: List<Offer>)
        fun showInfoMessage(msg: String)
    }

    interface Presenter {
        // User actions
        fun offerItemItemClicked(offer: Offer?)

        // Model updates
        fun onViewCreated()

        fun onDestroy()
    }

    interface Interactor {
        fun loadOffersList()
    }

    interface InteractorOutput {
        fun onQuerySuccess(data: List<Offer>)
        fun onQueryError()
    }
}
