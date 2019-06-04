package com.dlogan.android.offers.interactor

import android.annotation.SuppressLint
import com.dlogan.android.offers.OffersDemoApplication
import com.dlogan.android.offers.OffersListContract
import com.dlogan.android.offers.entity.OffersRepository
import com.dlogan.android.offers.utilities.LogUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class OfferListInteractor constructor(private var outputContract: OffersListContract.InteractorOutput) :
    OffersListContract.Interactor {

    companion object {
        val TAG: String by lazy { OfferListInteractor::class.java.simpleName }
    }

    init {
        OffersDemoApplication.INSTANCE.appComponent.inject(this)
    }

    @Inject
    lateinit var offersRepository: OffersRepository

    @SuppressLint("CheckResult")
    override fun loadOffersList() {
        offersRepository.getOffers().subscribeOn(Schedulers.computation())
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


