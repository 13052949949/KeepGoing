package com.gyt.kotlinbase

import android.app.Application
import android.content.Context

class BaseApplication:Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        currentApplication = this
    }


    companion object{

        @get:JvmName("getApplicationContext")
        lateinit var currentApplication: Context
    }

}