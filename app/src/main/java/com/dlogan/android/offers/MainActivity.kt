package com.dlogan.android.offers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProviders.of(this).get(OffersViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create adapter for the RecyclerView
        val adapter = OfferAdapter()
        offer_list.adapter = adapter

        // Subscribe the adapter to the ViewModel, so the items in the adapter are refreshed
        subscribeUi(adapter)
    }

    private fun subscribeUi(adapter: OfferAdapter) {
        viewModel.pagedOffers.observe(this, Observer { offers ->
            if (offers != null) {
                adapter.submitList(offers)
            }
        })
    }
}
