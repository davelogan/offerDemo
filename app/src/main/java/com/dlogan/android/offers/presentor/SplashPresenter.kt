package com.dlogan.android.offers.presentor

import com.dlogan.android.offers.OffersDemoApplication
import com.dlogan.android.offers.SplashContract
import com.dlogan.android.offers.view.activities.OffersListActivity
import ru.terrakok.cicerone.Router


class SplashPresenter(private var view: SplashContract.View?) : SplashContract.Presenter {

    private val router: Router? by lazy { OffersDemoApplication.INSTANCE.cicerone.router }

    override fun onViewCreated() {
        router?.navigateTo(OffersListActivity.TAG)
        view?.finishView()
    }

    override fun onDestroy() {
        view = null
    }
}