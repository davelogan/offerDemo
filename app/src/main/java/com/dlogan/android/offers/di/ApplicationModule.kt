package com.dlogan.android.offers.di

import android.content.Context
import com.dlogan.android.offers.OffersDemoApplication
import com.dlogan.android.offers.Router
import com.dlogan.android.offers.data.AppDatabase
import com.dlogan.android.offers.entity.OffersRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: OffersDemoApplication) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = application

    @Provides
    @Singleton
    fun providesOffersRepository(): OffersRepository {
        return OffersRepository(AppDatabase.getInstance(application.applicationContext))
    }

    // Routing layer (VIPER)
    @Provides
    @Singleton
    fun providesRouter(): Router {
        return Router()
    }
}
