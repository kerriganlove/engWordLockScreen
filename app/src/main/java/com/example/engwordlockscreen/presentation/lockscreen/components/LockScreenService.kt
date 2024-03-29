package com.example.engwordlockscreen.presentation.lockscreen.components

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.IBinder
import dagger.hilt.android.AndroidEntryPoint
import java.security.SecureRandom

@AndroidEntryPoint
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
                        val secureRandom = SecureRandom()
                        val quizNum = secureRandom.nextInt(2)
                        lockIntent.putExtra("quizUI",quizNum)
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
        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channel = NotificationChannel("Background", "Bye",NotificationManager.IMPORTANCE_NONE)
            nm.createNotificationChannel(channel)
            val notification = Notification.Builder(this,channel.id).build()
            startForeground(1,notification)
            //stopForeground(true)
        }

    }
    // Service 실행시 1번 발생.

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val filter = IntentFilter().apply { addAction(Intent.ACTION_SCREEN_OFF) }
        registerReceiver(receiver,filter)

        return START_STICKY
    } // Service가 Activity와 같은 Controller에서 시작하도록 했을 경우.

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}