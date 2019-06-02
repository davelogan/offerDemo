package com.dlogan.android.offers.view.activities

import android.os.Bundle
import android.widget.Toast
import com.dlogan.android.offers.OfferDetailsContract
import com.dlogan.android.offers.R
import com.dlogan.android.offers.entity.Offer
import com.dlogan.android.offers.presentor.OfferDetailsPresenter
import com.dlogan.android.offers.utilities.OFFER_KEY
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class OfferDetailActivity : BaseActivity(), OfferDetailsContract.View {

    companion object {
        val TAG by lazy { OfferDetailActivity::class.java.simpleName }
    }

    val navigator: Navigator by lazy(mode = LazyThreadSafetyMode.NONE) {
        SupportAppNavigator(this, -1)
    }

    @Inject
    lateinit var cicerone: Cicerone<Router>

    private var presenter: OfferDetailsContract.Presenter? = null

    var offer: Offer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_offer_detail)

        appComponent.inject(this)
        presenter = OfferDetailsPresenter(this)

        offer = savedInstanceState?.getParcelable(OFFER_KEY)

    }

    override fun onResume() {
        super.onResume()
        presenter?.onViewCreated(offer)
        cicerone.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        cicerone.navigatorHolder.removeNavigator()
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        presenter = null
        super.onDestroy()
    }

    override fun showInfoMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}