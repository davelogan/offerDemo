package com.dlogan.android.offers.view.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.dlogan.android.offers.OffersDemoApplication
import com.dlogan.android.offers.Router
import com.dlogan.android.offers.di.ApplicationComponent
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (this.application as OffersDemoApplication).appComponent
    }

    override fun onResume() {
        super.onResume()

        this.getToolbarInstance()?.let { this.initView(it) }
    }

    private fun initView(toolbar: Toolbar) {
        // Toolbar setup
        setSupportActionBar(toolbar)   // Replaces the 'ActionBar' with the 'Toolbar'
    }

    abstract fun getToolbarInstance(): Toolbar?

    @Inject
    lateinit var router: Router

    fun getContext(): Context {
        return this
    }
}


