package com.dlogan.android.offers.presentor

import com.dlogan.android.offers.SplashContract
import com.dlogan.android.offers.view.screens.OffersListScreen
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router


class SplashPresenter(private var view: SplashContract.View?, var cicerone: Cicerone<Router>) :
    SplashContract.Presenter {

    override fun onViewCreated() {
        cicerone.router?.navigateTo(OffersListScreen())
        view?.finishView()
    }

    override fun onDestroy() {
        view = null
    }
}