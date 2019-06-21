package com.dlogan.android.offers.view.activities

import android.content.Context
import android.content.Intent
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

class OfferDetailActivity : BaseActivity(), OfferDetailsContract.View {

    companion object {
        val TAG: String by lazy { OfferDetailActivity::class.java.simpleName }

        fun callingIntent(context: Context, offerId: String?): Intent {
            val intent = Intent(context, OfferDetailActivity::class.java)
            intent.putExtra(OFFER_KEY, offerId)
            return intent
        }
    }

    private var presenter: OfferDetailsContract.Presenter? = null

    var offer: Offer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_offer_detail)

        appComponent.inject(this)
        presenter = OfferDetailsPresenter(this, router)

        favorite.setOnClickListener {
            presenter?.favoriteCbClicked(offer, favorite.isChecked)
        }
    }

    override fun onResume() {
        super.onResume()

        val offerId = intent?.getStringExtra(OFFER_KEY)
        LogUtil.d(TAG, String.format("Got offer: %s",offerId))

        presenter?.onViewCreated(offerId)

        supportActionBar?.let {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        presenter = null
        super.onDestroy()
    }

    override fun showOfferData(offer: Offer?) {
        this.offer = offer
        name.text = offer?.name
        currentValue.text = offer?.currentValue
        description.text = offer?.description
        terms.text = offer?.terms
        favorite.isChecked = offer?.isFavorite ?: false
        Glide.with(this)
            .load(offer?.url)
            .error(R.drawable.baseline_insert_photo_black_48)
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