package com.dlogan.android.offers.data.loader

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.dlogan.android.offers.data.AppDatabase
import com.dlogan.android.offers.entity.Offer
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

    companion object {
        private val TAG by lazy { OfferDatabaseWorker::class.java.simpleName }
    }

    override val coroutineContext = Dispatchers.IO

    override suspend fun doWork(): Result = coroutineScope {

        try {
            applicationContext.assets.open(OFFERS_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val offerType = object : TypeToken<List<OfferDto>>() {}.type
                    val offerList: List<OfferDto> = Gson().fromJson(jsonReader, offerType)

                    val database = AppDatabase.getInstance(applicationContext)

                    //convert Dto to Entity
                    val offers: List<Offer> = offerList.map {
                        Offer(
                            it.id,
                            it.url,
                            it.name,
                            it.description,
                            it.terms,
                            it.currentValue,
                            false
                        )
                    }

                    database.offerDao().insertAll(offers)

                    Result.success()
                }
            }
        } catch (ex: Exception) {
            LogUtil.e(TAG, "Error loading Offer database", ex)
            Result.failure()
        }
    }
}