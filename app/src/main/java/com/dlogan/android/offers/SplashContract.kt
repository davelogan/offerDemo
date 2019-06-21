package com.dlogan.android.offers

import android.content.Context

interface SplashContract {
    interface View {
        fun finishView()
        fun getContext(): Context
    }

    interface Presenter {
        // Model updates
        fun onViewCreated()

        fun onDestroy()
    }
}
