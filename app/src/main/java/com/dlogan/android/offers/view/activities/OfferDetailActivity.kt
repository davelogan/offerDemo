package com.dlogan.android.offers.view.activities

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.dlogan.android.offers.OfferDetailsContract
import com.dlogan.android.offers.R
import com.dlogan.android.offers.entity.Offer
import com.dlogan.android.offers.presentor.OfferDetailsPresenter
import com.dlogan.android.offers.utilities.LogUtil
import com.dlogan.android.offers.utilities.OFFER_KEY
import kotlinx.android.synthetic.main.activity_offer_detail.*
import kotlinx.android.synthetic.main.toolbar_view_custom_layout.*
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class OfferDetailActivity : BaseActivity(), OfferDetailsContract.View {

    companion object {
        val TAG: String by lazy { OfferDetailActivity::class.java.simpleName }
    }

    private val navigator: Navigator by lazy(mode = LazyThreadSafetyMode.NONE) {
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
        presenter = OfferDetailsPresenter(this, cicerone)

        favorite.setOnClickListener {
            presenter?.favoriteCbClicked(offer, favorite.isChecked)
        }
    }

    override fun onResume() {
        super.onResume()

        offer = intent?.getParcelableExtra(OFFER_KEY)
        LogUtil.d(TAG, String.format("Got offer: %s", offer?.id))

        presenter?.onViewCreated(offer)
        cicerone.navigatorHolder.setNavigator(navigator)

        supportActionBar?.let {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
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

    override fun showOfferData(offer: Offer?) {
        name.text = offer?.name
        currentValue.text = offer?.currentValue
        description.text = offer?.description
        terms.text = offer?.terms
        favorite.isChecked = offer?.isFavorite ?: false
        Glide.with(this)
            .load(offer?.url)
            .into(this.imageView)
    }

    override fun showInfoMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun getToolbarInstance(): Toolbar? = toolbar_widget

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                presenter?.backButtonClicked()
                true
            }
            else -> false
        }
    }
}