package com.example.engwordlockscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class LoadingActivity : AppCompatActivity() {
    private val LOADING_TIME_OUT : Long = 2000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)
        startLoading()
    }
    private fun startLoading()
    {
        Handler(Looper.getMainLooper()).postDelayed({

        },LOADING_TIME_OUT)
    }
}