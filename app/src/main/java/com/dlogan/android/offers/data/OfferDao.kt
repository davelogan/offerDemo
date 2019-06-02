package com.dlogan.android.offers.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable

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
}
