package com.dlogan.android.offers.interactor

import android.annotation.SuppressLint
import com.dlogan.android.offers.OfferDetailsContract
import com.dlogan.android.offers.OffersDemoApplication
import com.dlogan.android.offers.entity.Offer
import com.dlogan.android.offers.entity.OffersRepository
import com.dlogan.android.offers.utilities.LogUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class OfferDetailsInteractor constructor(private var outputContract: OfferDetailsContract.InteractorOutput) :
    OfferDetailsContract.Interactor {

    companion object {
        val TAG: String by lazy { OfferDetailsInteractor::class.java.simpleName }
    }

    init {
        OffersDemoApplication.INSTANCE.appComponent.inject(this)
    }

    @Inject
    lateinit var offersRepository: OffersRepository

    override fun favoriteOffer(offer: Offer?, favorite: Boolean) {
        offer?.let {
            offer.isFavorite = favorite

            offersRepository.update(offer).subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ count ->
                    if (count.equals(1)) {
                        outputContract.onFavoriteOfferSuccess(offer)
                    } else {
                        LogUtil.e(TAG, "Error updating offer")
                        offer.isFavorite = !favorite
                        outputContract.onFavoriteOfferError()
                    }
                }, { e ->
                    outputContract.onFavoriteOfferError()
                    LogUtil.e(TAG, "Error updating offer", e)
                })
        }
    }

    @SuppressLint("CheckResult")
    override fun loadOffer(offerId: String?) {
        offerId?.let {
            offersRepository.getOffer(it).subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ offer ->
                    LogUtil.d(TAG, "Got %d offer")
                    outputContract.onLoadOfferSuccess(offer)
                }, { e ->
                    LogUtil.e(TAG, "Error when getting offers", e)
                    outputContract.onLoadOfferError()
                })
        }
    }
}


