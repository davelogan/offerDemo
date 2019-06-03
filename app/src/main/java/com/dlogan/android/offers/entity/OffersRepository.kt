package com.dlogan.android.offers.entity

import com.dlogan.android.offers.data.AppDatabase
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

class OffersRepository constructor(private val appDatabase: AppDatabase) {

    fun getOffers(): Flowable<List<Offer>> {
        return appDatabase.offerDao().getOffers()
    }

    fun update(offer: Offer): Single<Integer> {
        return appDatabase.offerDao().update(offer)
    }
}