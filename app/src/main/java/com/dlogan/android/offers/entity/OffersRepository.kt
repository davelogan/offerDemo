package com.dlogan.android.offers.entity

import com.dlogan.android.offers.data.AppDatabase
import io.reactivex.Flowable
import io.reactivex.Single

class OffersRepository constructor(private val appDatabase: AppDatabase) : OffersRepo{

    override fun getOfferHeaders(): Flowable<List<OfferHeader>> {
        return appDatabase.offerDao().getOfferHeaders()
    }

    override fun getOffer(offerId: String): Single<Offer> {
        return appDatabase.offerDao().getOffer(offerId)
    }


    override fun update(offer: Offer): Single<Int> {
        return appDatabase.offerDao().update(offer)
    }
}