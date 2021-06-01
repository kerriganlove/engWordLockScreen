package com.example.engwordlockscreen.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.engwordlockscreen.fragments.*

class MainViewPagerAdapter(fa : FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return 5;
    }

    override fun createFragment(position: Int): Fragment {
        var fragment : Fragment? = null
        fragment = when(position) {
            0 -> {
                WordInsertFragment()

            }
            1 -> {
                WordListFragment()
            }
            2 -> {
                IdiomInsertFragment()
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
        return fragment!!
    }

}