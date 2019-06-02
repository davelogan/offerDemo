package com.dlogan.android.offers.entity

import com.dlogan.android.offers.data.AppDatabase
import io.reactivex.Flowable

class OffersRepository constructor(private val appDatabase: AppDatabase) {

    fun getOffers(): Flowable<List<Offer>> {
        return appDatabase.offerDao().getOffers()
    }
}