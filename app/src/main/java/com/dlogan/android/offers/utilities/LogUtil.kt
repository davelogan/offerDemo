package com.dlogan.android.offers.utilities

import android.util.Log
import com.dlogan.android.offers.BuildConfig


object LogUtil {

    fun d(tag: String, message: String) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, message)
        }
    }

    fun e(tag: String, message: String, exception: Throwable) {
        Log.e(tag, message, exception)
    }
}