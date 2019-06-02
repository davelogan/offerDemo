package com.dlogan.android.offers.view.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.dlogan.android.offers.OffersDemoApplication
import com.dlogan.android.offers.SplashContract
import com.dlogan.android.offers.di.ApplicationComponent
import com.dlogan.android.offers.presentor.SplashPresenter
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward
import javax.inject.Inject

class SplashActivity : AppCompatActivity(), SplashContract.View {

//    private val navigator: Navigator? by lazy {
//        object : Navigator {
//            override fun applyCommand(command: Command) {
//                if (command is Forward) {
//                    forward(command)
//                }
//            }
//
//            private fun forward(command: Forward) {
//                when (command.screenKey) {
//                    OffersListActivity.TAG -> startActivity(Intent(this@SplashActivity, OffersListActivity::class.java))
//                    else -> Log.e("Cicerone", "Unknown screen: " + command.screenKey)
//                }
//            }
//        }
//    }

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (this.application as OffersDemoApplication).appComponent
    }

    val navigator: Navigator by lazy(mode = LazyThreadSafetyMode.NONE) {
        SupportAppNavigator(this, -1)
    }

    private var presenter: SplashContract.Presenter? = null

    @Inject
    lateinit var cicerone: Cicerone<Router>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        presenter = SplashPresenter(this, cicerone)
    }

    override fun onResume() {
        super.onResume()
        cicerone.navigatorHolder.setNavigator(navigator)
        presenter?.onViewCreated()
    }

    override fun onPause() {
        super.onPause()
        cicerone.navigatorHolder.removeNavigator()
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
}
