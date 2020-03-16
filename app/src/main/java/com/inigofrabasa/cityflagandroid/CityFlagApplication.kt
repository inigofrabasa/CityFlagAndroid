package com.inigofrabasa.cityflagandroid

import android.app.Application

class CityFlagApplication : Application() {

    var appContext = this

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: CityFlagApplication
            private set
    }
}