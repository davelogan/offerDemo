package com.dlogan.android.offers

import android.app.Application
import com.dlogan.android.offers.di.ApplicationComponent
import com.dlogan.android.offers.di.ApplicationModule
import com.dlogan.android.offers.di.DaggerApplicationComponent

import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

class OffersDemoApplication : Application() {

    companion object {
        lateinit var INSTANCE: OffersDemoApplication
    }

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    init {
        INSTANCE = this
    }

    // Routing layer (VIPER)
    lateinit var cicerone: Cicerone<Router>

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        this.injectMembers()
        this.initCicerone()
    }

    private fun injectMembers() = appComponent.inject(this)


    private fun initCicerone() {
        this.cicerone = Cicerone.create()
    }
}