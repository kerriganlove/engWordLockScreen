package com.example.engwordlockscreen.presentation.splash.components

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.engwordlockscreen.presentation.main.components.MainActivity
import com.example.engwordlockscreen.R
import com.example.engwordlockscreen.presentation.lockscreen.LockScreenService
import com.example.engwordlockscreen.presentation.utils.dialogs.CustomDialog

class LoadingActivity : AppCompatActivity() {
    companion object {
        private const val LOADING_TIME_OUT : Long = 2000
    }
    private lateinit var getResult : ActivityResultLauncher<Intent>

    private val dlgUtil = CustomDialog(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)
        showPopUp()
    }

    private fun showPopUp() {
        dlgUtil.checkPermissionDialog {
            checkPermission()
        }
    }
    // TODO 권한 관련 팝업 만들기.
    private fun startLoading()
    {
        val intent = Intent(application, MainActivity::class.java)
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(intent)
            finish()
        }, LOADING_TIME_OUT)
    }

    private fun checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if (!Settings.canDrawOverlays(this))
            {
                var uri = Uri.fromParts("package", packageName, null)
                val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, uri)
                getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
                {
                    if ( it.resultCode == RESULT_CANCELED)
                    {
                        if (!Settings.canDrawOverlays(this))
                        {
                            Toast.makeText(this,"반드시 동의하셔야만 합니다.", Toast.LENGTH_LONG).show()
                        }
                    }
                    else
                    {
                        startModule()
                    }
                }
                getResult.launch(intent)
            }
            else
            {
                startModule()
                startLoading()
            }
        }
        else
        {
            startModule()
            startLoading()
        }
    }

    private fun startModule()
    {
        val intent = Intent(applicationContext, LockScreenService::class.java)
        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(intent)
            Log.d("startService","Service")
        }
        else
        {
            startService(intent)
        }
    }
}