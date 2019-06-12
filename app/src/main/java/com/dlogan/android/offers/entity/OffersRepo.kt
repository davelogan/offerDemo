package com.dlogan.android.offers.entity

import io.reactivex.Flowable
import io.reactivex.Single

interface OffersRepo {

    fun getOfferHeaders(): Flowable<List<OfferHeader>>

    fun getOffer(offerId: String): Single<Offer>


    fun update(offer: Offer): Single<Int>
}