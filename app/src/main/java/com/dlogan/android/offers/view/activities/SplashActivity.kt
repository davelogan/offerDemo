package com.dlogan.android.offers.view.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.dlogan.android.offers.OffersDemoApplication
import com.dlogan.android.offers.SplashContract
import com.dlogan.android.offers.presentor.SplashPresenter
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward

class SplashActivity : AppCompatActivity(), SplashContract.View {

    private val navigator: Navigator? by lazy {
        object : Navigator {
            override fun applyCommand(command: Command) {
                if (command is Forward) {
                    forward(command)
                }
            }

            private fun forward(command: Forward) {
                when (command.screenKey) {
                    OffersListActivity.TAG -> startActivity(Intent(this@SplashActivity, OffersListActivity::class.java))
                    else -> Log.e("Cicerone", "Unknown screen: " + command.screenKey)
                }
            }
        }
    }
    private var presenter: SplashContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = SplashPresenter(this)
    }

    override fun onResume() {
        super.onResume()
        OffersDemoApplication.INSTANCE.cicerone.navigatorHolder.setNavigator(navigator)
        presenter?.onViewCreated()
    }

    override fun onPause() {
        super.onPause()
        OffersDemoApplication.INSTANCE.cicerone.navigatorHolder.removeNavigator()
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
