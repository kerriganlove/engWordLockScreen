package com.example.engwordlockscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.engwordlockscreen.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
    private fun itemSelect()
    {
        binding.mainBottomNavigation.setOnNavigationItemSelectedListener()
    }
}