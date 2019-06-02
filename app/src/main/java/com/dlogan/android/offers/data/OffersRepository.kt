package com.dlogan.android.offers.data

import dagger.Module
import io.reactivex.Flowable
import javax.inject.Inject

@Module
class OffersRepository @Inject constructor(private val appDatabase: AppDatabase) {

    fun getOffers(): Flowable<List<Offer>> {
        return appDatabase.offerDao().getOffers()
    }


}