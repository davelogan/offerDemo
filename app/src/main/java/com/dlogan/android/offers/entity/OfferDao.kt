package com.dlogan.android.offers.entity

import androidx.room.*
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * The Data Access Object for the Offer.
 */
@Dao
interface OfferDao {

    @Query("SELECT * FROM offers")
    fun getOffers(): Flowable<List<Offer>>

    @Query("SELECT * FROM offers WHERE id = :offerId")
    fun getOffer(offerId: String): List<Offer>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(offers: List<Offer>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(offer: Offer): Single<Integer>
}
