package com.dlogan.android.offers.interactor

import android.annotation.SuppressLint
import com.dlogan.android.offers.OfferDetailsContract
import com.dlogan.android.offers.OffersDemoApplication
import com.dlogan.android.offers.entity.Offer
import com.dlogan.android.offers.entity.OffersRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class OfferDetailsInteractor constructor(private var outputContract: OfferDetailsContract.InteractorOutput) :
    OfferDetailsContract.Interactor {

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
                    if (count.equals(1)) {
                        outputContract.onSetAtFavoriteSuccess(offer)
                    } else {
                        offer.isFavorite = !favorite
                        outputContract.onSetAtFavoriteError()
                    }
                }, { e ->
                    outputContract.onSetAtFavoriteError()
                })
        }
    }
}


