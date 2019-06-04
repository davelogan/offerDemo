
package com.dlogan.android.offers.view.activities

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.dlogan.android.offers.R
import com.dlogan.android.offers.view.adapters.OfferListItemViewHolder
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations

class OffersListActivityInstrumentedTest {

    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule<OffersListActivity>(OffersListActivity::class.java)

//    @Rule
//    @JvmField
//    var rule = MockitoJUnit.rule()

//    @Mock
//    @JvmField
//    var offersRepo = Mockito.mock(OffersRepository::class.java)

//    @Rule @JvmField val injectMocks = InjectMocksRule.create(this)
//
//    @Mock
//    lateinit var offersRepo: OffersRepository

    @Before
    fun setUp() {

        MockitoAnnotations.initMocks(this)

        //TODO finish getting mock setup...
        //Getting this error org.mockito.exceptions.base.MockitoException:
        //Cannot mock/spy class com.dlogan.android.offers.entity.OffersRepository
        //Mockito cannot mock/spy because :
        //- final class


//        var offersRepo = Mockito.mock(OffersRepository::class.java)
//
//        val data = Flowable.fromArray(listOf(
//            Offer("111", "", "name1111", "descr111", "terms111", "cv111", false),
//            Offer("222", "", "name222", "descr222", "terms222", "cv222", false),
//            Offer("333", "", "name333", "descr333", "terms333", "cv333", true)))
//
//
//        Mockito.`when`(offersRepo.getOffers()).thenReturn(data)

    }


    @Test
    fun testRecyclerViewIsPopulated() {

        waitForSplashScreen()

        onView(withId(R.id.offers_list_activity))
            .check(matches(hasDescendant(withText("\$0.75 Cash Back"))))
    }

    @Test
    fun testRecyclerViewItemClickLaunchesDetailActivity() {

        waitForSplashScreen()

        //confirm main list exists
        assert(onView(withId(R.id.offers_list_activity)) != null)

        //click on 3rd item
        onView(withId(R.id.rv_offers_list_activity))
            .perform(RecyclerViewActions.scrollToPosition<OfferListItemViewHolder>(2))
            .perform(RecyclerViewActions.actionOnItemAtPosition<OfferListItemViewHolder>(2, click()))

        //confirm main list no longer exists
        assert(onView(withId(R.id.offers_list_activity)) == null)

        //confirm details exists
        assert(onView(withId(R.id.offer_details_sv)) != null)

        //confirm data is as expected
        onView(withId(R.id.name)).check(matches(withText("Girl Scout Cookie™ Inspired Baking Mix")))
        onView(withId(R.id.description)).check(matches(withText("Any variety - Any size")))
        onView(withId(R.id.terms)).check(matches(withText("Rebate valid on Girl Scout Cookie™ Inspired Baking Mix for any variety, any size.")))
        onView(withId(R.id.currentValue)).check(matches(withText("\$0.50 Cash Back")))

    }

    private fun waitForSplashScreen() {
        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}
