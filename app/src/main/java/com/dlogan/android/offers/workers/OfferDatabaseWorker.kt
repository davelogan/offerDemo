package com.dlogan.android.offers.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.dlogan.android.offers.data.AppDatabase
import com.dlogan.android.offers.data.Offer
import com.dlogan.android.offers.utilities.LogUtil
import com.dlogan.android.offers.utilities.OFFERS_DATA_FILENAME
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope

class OfferDatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    private val TAG by lazy { OfferDatabaseWorker::class.java.simpleName }
    override val coroutineContext = Dispatchers.IO

    override suspend fun doWork(): Result = coroutineScope {

        try {
            applicationContext.assets.open(OFFERS_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val offerType = object : TypeToken<List<Offer>>() {}.type
                    val offerList: List<Offer> = Gson().fromJson(jsonReader, offerType)

                    val database = AppDatabase.getInstance(applicationContext)
                    database.offerDao().insertAll(offerList)

                    Result.success()
                }
            }
        } catch (ex: Exception) {
            LogUtil.e(TAG, "Error loading Offer database", ex)
            Result.failure()
        }
    }
}