package com.dlogan.android.offers.interactor

import android.annotation.SuppressLint
import com.dlogan.android.offers.OfferDetailsContract
import com.dlogan.android.offers.OffersDemoApplication
import com.dlogan.android.offers.OffersListContract
import com.dlogan.android.offers.entity.Offer
import com.dlogan.android.offers.entity.OffersRepository
import com.dlogan.android.offers.utilities.LogUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class OfferDetailsInteractor :
    OfferDetailsContract.Interactor {


    companion object {
        val TAG by lazy { OfferDetailsInteractor::class.java.simpleName }
    }

//    init {
//        OffersDemoApplication.INSTANCE.appComponent.inject(this)
//    }

    @Inject
    lateinit var offersRepository: OffersRepository


    override fun favoriteOffer(offer: Offer, isFavorate: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}


