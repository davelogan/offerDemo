package com.dlogan.android.offers

import android.app.Application
import com.dlogan.android.offers.di.ApplicationComponent
import com.dlogan.android.offers.di.ApplicationModule
import com.dlogan.android.offers.di.DaggerApplicationComponent


class OffersDemoApplication : Application() {

    companion object {
        //TODO this smells! Need to find a good way for the Interactors to
        // be able to use injection without having the Application Component passed in. Not sure yet the best
        // way to do this with the VIPER architecture
        lateinit var INSTANCE: OffersDemoApplication
    }

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        this.injectMembers()
    }

    private fun injectMembers() {
        appComponent.inject(this)
    }
}