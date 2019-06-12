package com.dlogan.android.offers.presentor

import com.dlogan.android.offers.Router
import com.dlogan.android.offers.SplashContract


class SplashPresenter(private var view: SplashContract.View?, var router: Router) :
    SplashContract.Presenter {

    override fun onViewCreated() {
        router.showOffersList(view?.getContext())
        view?.finishView()
    }

    override fun onDestroy() {
        view = null
    }
}