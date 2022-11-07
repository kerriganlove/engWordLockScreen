package com.example.engwordlockscreen

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WordApp : Application() {
    init {
        instance = this
    }
    companion object {
        private lateinit var instance : WordApp
        fun appContext(): Context = instance.applicationContext
    }
}