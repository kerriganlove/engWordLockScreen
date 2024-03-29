package com.example.engwordlockscreen.presentation.main.components

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.engwordlockscreen.R
import com.example.engwordlockscreen.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings

@AndroidEntryPoint
@WithFragmentBindings
class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    fun getEmoji() : String {
        return String(Character.toChars(0x270D))
    }

    private fun init()
    {
        binding.mainBottomNavigation.setOnNavigationItemSelectedListener(this)
        binding.mainBottomNavigation.selectedItemId = R.id.action_word_insert
        binding.mainViewPager.adapter = MainViewPagerAdapter(this)
        binding.mainViewPager.registerOnPageChangeCallback(PageChangeCallBack())
        binding.mainViewPager.offscreenPageLimit = 5
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
            R.id.action_word_insert ->
            {
                binding.mainViewPager.currentItem = 0
                return true
            }
            R.id.action_word_list ->
            {
                binding.mainViewPager.currentItem = 1
                return true
            }
            R.id.action_idiom_insert ->
            {
                binding.mainViewPager.currentItem = 2
                return true
            }
            R.id.action_idiom_list ->
            {
                binding.mainViewPager.currentItem = 3
                return true
            }
            R.id.action_setting ->
            {
                binding.mainViewPager.currentItem = 4
                return true
            }
        }
        return true
    }
}