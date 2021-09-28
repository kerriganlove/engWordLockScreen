package com.example.engwordlockscreen

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.engwordlockscreen.adapters.MainViewPagerAdapter
import com.example.engwordlockscreen.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
    }
    private fun init()
    {
        binding.mainBottomNavigation.setOnNavigationItemSelectedListener(this)
        binding.mainBottomNavigation.selectedItemId = R.id.action_word_insert
        binding.mainViewPager.adapter = MainViewPagerAdapter(this)
        binding.mainViewPager.registerOnPageChangeCallback(PageChangeCallBack())

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