package com.example.engwordlockscreen.lockscreen

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.IBinder
import android.util.Log
import com.example.engwordlockscreen.R

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
                        Log.d("Hi","LockscreenService On")
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
        Log.d("Hi2","ServiceOn")
        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channel = NotificationChannel("Background", "Bye",NotificationManager.IMPORTANCE_MIN)
            nm.createNotificationChannel(channel)
            val notification = Notification.Builder(this,channel.id).build()
            startForeground(1,notification)
            stopForeground(true)
        }

        val filter = IntentFilter().apply { addAction(Intent.ACTION_SCREEN_OFF) }
        registerReceiver(receiver,filter)
    }
    // Service 실행시 1번 발생.

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)

        return Service.START_STICKY
    } // Service가 Activity와 같은 Controller에서 시작하도록 했을 경우.

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}