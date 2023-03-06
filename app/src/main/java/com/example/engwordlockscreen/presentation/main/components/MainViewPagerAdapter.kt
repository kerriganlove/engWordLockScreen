package com.example.engwordlockscreen.presentation.main.components

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.engwordlockscreen.presentation.fragments.*
import com.example.engwordlockscreen.presentation.main.setting.components.SettingFragment
import com.example.engwordlockscreen.presentation.main.word.components.insert.WordInsertFragment
import com.example.engwordlockscreen.presentation.main.word.components.wordlist.WordListFragment

class MainViewPagerAdapter(fa : FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return 5;
    }

    override fun createFragment(position: Int): Fragment {
        var fragment = when(position) {
            0 -> {
                WordInsertFragment()
            }
            1 -> {
                WordListFragment()
            }
            2 -> {
                WordSearchFragment()
            }
            3 -> {
                IdiomListFragment()
            }
            4 -> {
                SettingFragment()
            }
            else ->{
                error("Fucking Genius?")
            }
        }
        return fragment
    }

}