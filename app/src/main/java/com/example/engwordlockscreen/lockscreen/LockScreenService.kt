package com.example.engwordlockscreen.lockscreen

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder

class LockScreenService : Service()
{
    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if ( intent != null)
            {
                when(intent.action)
                {
                    Intent.ACTION_SCREEN_OFF ->
                    {
                        val lockIntent = Intent(applicationContext, LockScreenActivity::class.java)
                        lockIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(lockIntent)
                    }
                }
            }
        }
    } // Singleton

    override fun onBind(intent: Intent?): IBinder?
    {
        return null
    }

    override fun onCreate() {
        super.onCreate()


    }
    // Service 실행시 1번 발생.

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_SCREEN_OFF)
        registerReceiver(receiver,filter)
        return Service.START_STICKY
    } // Service가 Activity와 같은 Controller에서 시작하도록 했을 경우.

    override fun onDestroy() {
        super.onDestroy()
    }
}