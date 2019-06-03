package com.dlogan.android.offers.interactor

import android.annotation.SuppressLint
import com.dlogan.android.offers.OfferDetailsContract
import com.dlogan.android.offers.OffersDemoApplication
import com.dlogan.android.offers.OffersListContract
import com.dlogan.android.offers.entity.Offer
import com.dlogan.android.offers.entity.OffersRepository
import com.dlogan.android.offers.utilities.LogUtil
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executor
import javax.inject.Inject

class OfferDetailsInteractor constructor(var outputContract: OfferDetailsContract.InteractorOutput):
    OfferDetailsContract.Interactor {


    companion object {
        val TAG by lazy { OfferDetailsInteractor::class.java.simpleName }
    }

    init {
        OffersDemoApplication.INSTANCE.appComponent.inject(this)
    }

    @Inject
    lateinit var offersRepository: OffersRepository

    @SuppressLint("CheckResult")
    override fun favoriteOffer(offer: Offer?, favorite: Boolean) {
        offer?.let {
            offer.isFavorite = favorite

            offersRepository.update(offer).subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ count ->
                    //LogUtil.d(OfferListInteractor.TAG, String.format("Got %d offers", offers.size))
                    if (count.equals(1)) {
                        outputContract.onSetAtFavoriteSuccess(offer)
                    } else {
                        offer.isFavorite = !favorite
                        outputContract.onSetAtFavoriteError()
                    }
                }, { e ->
                    //LogUtil.e(OfferListInteractor.TAG, "Error when getting offers", e)
                    outputContract.onSetAtFavoriteError()
                })
        }
    }
}


