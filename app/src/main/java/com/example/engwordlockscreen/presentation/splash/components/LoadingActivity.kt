package com.example.engwordlockscreen.presentation.splash.components

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.engwordlockscreen.presentation.main.components.MainActivity
import com.example.engwordlockscreen.R

class LoadingActivity : AppCompatActivity() {
    private val LOADING_TIME_OUT : Long = 2000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)
        startLoading()
    }

    // TODO 권한 관련 팝업 만들기.
    private fun startLoading()
    {
        val intent = Intent(application, MainActivity::class.java)
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(intent)
            finish()
        },LOADING_TIME_OUT)
    }
}