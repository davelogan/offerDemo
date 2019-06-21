package com.dlogan.android.offers.view.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.dlogan.android.offers.OffersListContract
import com.dlogan.android.offers.R
import com.dlogan.android.offers.entity.OfferHeader
import com.dlogan.android.offers.presentor.OffersListPresenter
import com.dlogan.android.offers.view.adapters.OffersListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_view_custom_layout.*

class OffersListActivity : BaseActivity(), OffersListContract.View {



    companion object {
        val TAG: String by lazy { OffersListActivity::class.java.simpleName }

        fun callingIntent(context: Context): Intent {
            return Intent(context, OffersListActivity::class.java)
        }
    }


    private var presenter: OffersListContract.Presenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appComponent.inject(this)
        presenter = OffersListPresenter(this, router)
        rv_offers_list_activity.adapter = OffersListAdapter({ offerHeader -> offerHeader?.let {
            presenter?.offerItemItemClicked(
                it
            )
        } }, null)
    }

    override fun onResume() {
        super.onResume()
        presenter?.onViewCreated()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        presenter = null
        super.onDestroy()
    }

    override fun showLoading() {
        rv_offers_list_activity.isEnabled = false
        prog_bar_offers_list_activity.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        rv_offers_list_activity.isEnabled = true
        prog_bar_offers_list_activity.visibility = View.GONE
    }

    override fun showOffers(data: List<OfferHeader>) {
        (rv_offers_list_activity.adapter as OffersListAdapter).updateData(data)
    }

    override fun showInfoMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun getToolbarInstance(): Toolbar? = toolbar_widget

}
