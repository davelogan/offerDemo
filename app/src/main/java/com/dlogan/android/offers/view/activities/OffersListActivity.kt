package com.dlogan.android.offers.view.activities

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.Toast
import com.dlogan.android.offers.OffersDemoApplication
import com.dlogan.android.offers.OffersListContract
import com.dlogan.android.offers.R
import com.dlogan.android.offers.data.Offer
import com.dlogan.android.offers.presentor.OffersListPresenter
import com.dlogan.android.offers.utilities.LogUtil
import com.dlogan.android.offers.view.adapters.OffersListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward

class OffersListActivity : BaseActivity(), OffersListContract.View {

    companion object {
        val TAG by lazy { OffersListActivity::class.java.simpleName }
    }

    private val navigator: Navigator? by lazy {
        object : Navigator {
            override fun applyCommand(command: Command) {
                if (command is Forward) {
                    forward(command)
                }
            }

            private fun forward(command: Forward) {
                val data = (command.transitionData as Offer)

                when (command.screenKey) {
                    OfferDetailActivity.TAG -> startActivity(
                        Intent(this@OffersListActivity, OfferDetailActivity::class.java)
                            .putExtra("data", data as Parcelable)
                    )   // 4
                    else -> LogUtil.e("Cicerone", "Unknown screen: " + command.screenKey)
                }
            }
        }
    }

    private var presenter: OffersListContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = OffersListPresenter(this)
        rv_offers_list_activity.adapter = OffersListAdapter({ offer -> presenter?.offerItemItemClicked(offer) }, null)
        rv_offers_list_activity.adapter = OffersListAdapter({ offer -> presenter?.offerItemItemClicked(offer) }, null)
    }

    override fun onResume() {
        super.onResume()
        presenter?.onViewCreated()
        OffersDemoApplication.INSTANCE.cicerone.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        OffersDemoApplication.INSTANCE.cicerone.navigatorHolder.removeNavigator()
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

    override fun publishDataList(data: List<Offer>) {
        (rv_offers_list_activity.adapter as OffersListAdapter).updateData(data)
    }

    override fun showInfoMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
