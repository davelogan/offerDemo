package com.dlogan.android.offers.data

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dlogan.android.offers.entity.Offer
import org.junit.*
import java.util.Arrays.asList

class AppDatabaseTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var mDatabase: AppDatabase? = null

    private val myOffer = Offer("111", "hrl", "nnn", "ddddd", "tttt", "cccc", true)
    private val myOffer2 = Offer("222", "hrl", "nnn222", "ddddd222", "tttt222", "cccc222", true)

    @Before
    @Throws(Exception::class)
    fun initDb() {
        mDatabase = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            AppDatabase::class.java!!
        )
            // allowing main thread queries, just for testing
            .allowMainThreadQueries()
            .build()
    }

    @After
    @Throws(Exception::class)
    fun closeDb() {
        mDatabase?.close()
    }

    @Test
    fun insertAndGetOfferById() {
        // Given that we have a offer in the data source
        mDatabase!!.offerDao()!!.insertAll(asList(myOffer))

        mDatabase!!.offerDao().getOffer(myOffer.id)
            .test()
            // assertValue asserts that there was only one emission of the offer
            .assertValue { offer: Offer ->
                // The emitted offer is the expected one
                offer!!.id == myOffer.id &&
                        offer!!.isFavorite == myOffer.isFavorite &&
                        offer!!.name == myOffer.name
            }
    }

    @Test
    fun insertAndGetOffers() {
        // Given that we have a offer in the data source
        mDatabase!!.offerDao()!!.insertAll(asList(myOffer, myOffer2))

        mDatabase!!.offerDao().getOffers()
            .test()
            // assertValue asserts that there are two offers
            .assertValue { offers: List<Offer> ->
                offers.size == 2
            }
    }
}