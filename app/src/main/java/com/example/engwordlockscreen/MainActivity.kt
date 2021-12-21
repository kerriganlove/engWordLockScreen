package com.example.engwordlockscreen

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.engwordlockscreen.adapters.MainViewPagerAdapter
import com.example.engwordlockscreen.databinding.ActivityMainBinding
import com.example.engwordlockscreen.lockscreen.LockScreenService
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var getResult : ActivityResultLauncher<Intent>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
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
                            Toast.makeText(this,"반드시 동의하셔야만 합니다.",Toast.LENGTH_LONG).show()
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
            }
        }
        else
        {
            startModule()
        }
    }
    private fun init()
    {
        binding.mainBottomNavigation.setOnNavigationItemSelectedListener(this)
        binding.mainBottomNavigation.selectedItemId = R.id.action_word_insert
        binding.mainViewPager.adapter = MainViewPagerAdapter(this)
        binding.mainViewPager.registerOnPageChangeCallback(PageChangeCallBack())
        checkPermission()
    }
    private inner class PageChangeCallBack : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            binding.mainBottomNavigation.selectedItemId = when(position)
            {
                0 -> R.id.action_word_insert
                1 -> R.id.action_word_list
                2 -> R.id.action_idiom_insert
                3 -> R.id.action_idiom_list
                4 -> R.id.action_setting
                else -> error("Fucking Genius 2")
            }
        }
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.action_word_insert->
            {
                binding.mainViewPager.currentItem = 0
                return true
            }
            R.id.action_word_list->
            {
                binding.mainViewPager.currentItem = 1
                // binding.mainBottomNavigation.visibility = View.GONE
                return true
            }
            R.id.action_idiom_insert->
            {
                binding.mainViewPager.currentItem = 2
                return true
            }
            R.id.action_idiom_list->
            {
                binding.mainViewPager.currentItem = 3
                return true
            }
            R.id.action_setting->
            {
                binding.mainViewPager.currentItem = 4
                return true
            }
        }
        return true
    }
}