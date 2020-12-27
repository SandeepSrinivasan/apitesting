package com.dynamicdudes.apitesting

import android.app.Application

class AComputerEngineerApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AppPreferences.init(this)
    }
}