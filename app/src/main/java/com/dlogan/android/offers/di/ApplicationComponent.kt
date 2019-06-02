package com.dlogan.android.offers.di

import com.dlogan.android.offers.OffersDemoApplication
import com.dlogan.android.offers.data.OffersRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, OffersRepository::class])
interface ApplicationComponent {

    fun inject(application: OffersDemoApplication)
    //fun inject(offersRepository: OffersRepository)


}
