package com.dlogan.android.offers.view.activities

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dlogan.android.offers.OffersDemoApplication
import com.dlogan.android.offers.Router
import com.dlogan.android.offers.SplashContract
import com.dlogan.android.offers.di.ApplicationComponent
import com.dlogan.android.offers.presentor.SplashPresenter
import javax.inject.Inject

class SplashActivity : AppCompatActivity(), SplashContract.View {


    private val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (this.application as OffersDemoApplication).appComponent
    }

    private var presenter: SplashContract.Presenter? = null

    @Inject
    lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        presenter = SplashPresenter(this, router)
    }

    override fun onResume() {
        super.onResume()
        //cicerone.navigatorHolder.setNavigator(navigator)
        presenter?.onViewCreated()
    }

    override fun onPause() {
        super.onPause()
        //cicerone.navigatorHolder.removeNavigator()
    }

    override fun finishView() {
        // close splash activity
        finish()
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        presenter = null
        super.onDestroy()
    }

    override fun getContext(): Context {
        return this
    }
}
