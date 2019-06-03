package com.dlogan.android.offers.di

import com.dlogan.android.offers.OffersDemoApplication
import com.dlogan.android.offers.interactor.OfferDetailsInteractor
import com.dlogan.android.offers.interactor.OfferListInteractor
import com.dlogan.android.offers.view.activities.OfferDetailActivity
import com.dlogan.android.offers.view.activities.OffersListActivity
import com.dlogan.android.offers.view.activities.SplashActivity
import dagger.Component
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: OffersDemoApplication)
    fun inject(offerListInteractor: OfferListInteractor)
    fun inject(offerDetailsInteractor: OfferDetailsInteractor)

    fun inject(router: Cicerone<Router>)

    fun inject(splashActivity: SplashActivity)
    fun inject(offersListActivity: OffersListActivity)
    fun inject(offerDetailActivity: OfferDetailActivity)

}
