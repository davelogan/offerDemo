package com.dlogan.android.offers.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.dlogan.android.offers.data.loader.OfferDatabaseWorker
import com.dlogan.android.offers.entity.Offer
import com.dlogan.android.offers.entity.OfferDao
import com.dlogan.android.offers.utilities.DATABASE_NAME

/**
 * The Room database for this app
 */
@Database(entities = [Offer::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun offerDao(): OfferDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, DATABASE_NAME
            )
                // prepopulate the database after onCreate was called
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        // insert the data
                        val request = OneTimeWorkRequestBuilder<OfferDatabaseWorker>().build()
                        WorkManager.getInstance().enqueue(request)
                    }
                })
                .build()

    }
}
