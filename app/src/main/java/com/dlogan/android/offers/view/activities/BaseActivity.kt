package com.dlogan.android.offers.view.activities

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onResume() {
        super.onResume()

        //this.getToolbarInstance()?.let { this.initView(it) }
    }

//    private fun initView(toolbar: Toolbar) {
//        // Toolbar setup
//        setSupportActionBar(toolbar)   // Replaces the 'ActionBar' with the 'Toolbar'
//    }
//
//    abstract fun getToolbarInstance(): Toolbar?
}
