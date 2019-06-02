package com.dlogan.android.offers.interactor

import android.annotation.SuppressLint
import android.content.Context
import com.dlogan.android.offers.OffersListContract
import com.dlogan.android.offers.data.AppDatabase
import com.dlogan.android.offers.utilities.LogUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class OfferListInteractor @Inject constructor(
    private val context: Context,
    var outputContract: OffersListContract.InteractorOutput
) : OffersListContract.Interactor {

    companion object {
        val TAG by lazy { OfferListInteractor::class.java.simpleName }
    }

    @SuppressLint("CheckResult")
    override fun loadOffersList() {
        AppDatabase.getInstance(context).offerDao().getOffers().subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ offers ->
                LogUtil.d(TAG, String.format("Got %d offers", offers.size))
                outputContract.onQuerySuccess(offers)
            }, { e ->
                LogUtil.e(TAG, "Error when getting offers", e)
                outputContract.onQueryError()
            })
    }

}


