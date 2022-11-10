package com.example.engwordlockscreen.presentation.splash.components

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
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
import androidx.annotation.RequiresApi
import com.example.engwordlockscreen.presentation.main.components.MainActivity
import com.example.engwordlockscreen.R
import com.example.engwordlockscreen.WordApp
import com.example.engwordlockscreen.presentation.lockscreen.LockScreenService
import com.example.engwordlockscreen.presentation.utils.ConstUtil
import com.example.engwordlockscreen.presentation.utils.ToastUtil
import com.example.engwordlockscreen.presentation.utils.dialogs.CustomDialog

class LoadingActivity : AppCompatActivity() {
    companion object {
        private const val LOADING_TIME_OUT : Long = 3000
    }
    private lateinit var getResult : ActivityResultLauncher<Intent>


    private val dlgUtil = CustomDialog(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)
        showPopUp()
    }

    private fun setResult() {
        getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            enterSeveral()
        }
    }

    private fun showPopUp() {
        val pref = getSharedPreferences(ConstUtil.FIRST_APP_INSTALL, Activity.MODE_PRIVATE)
        val first = pref.getBoolean(ConstUtil.FIRST_APP_INSTALL, false)
        if ( !first ) {
            pref.edit().putBoolean(ConstUtil.FIRST_APP_INSTALL, true).apply()
            setResult()
            enterFirst()
        } else {
            enterSeveral()
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


    private fun startModule()
    {
        val intent = Intent(applicationContext, LockScreenService::class.java)
        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(intent)
        }
        else
        {
            startService(intent)
        }
    }

    private fun enterSeveral() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                ToastUtil.permissionToast(this)
                startLoading()
            }
            else {
                startModule()
                startLoading()
            }
        } else {
            startModule()
            startLoading()
        }
    }

    private fun enterFirst() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            dlgUtil.wordDeleteFunction {
                var uri = Uri.fromParts("package", packageName, null)
                val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, uri)
                getResult.launch(intent)
            }
        }
        else {
            startModule()
            startLoading()
        }
    }
}