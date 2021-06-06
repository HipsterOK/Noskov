package com.example.developerslife.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.developerslife.R

class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                FirstFragment()
            }
            1 -> {
                SecondFragment()
            }
            else -> {
                ThirdFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position){
            0 -> "Последние"
            1 -> "Лучшие"
            else -> {
                "Горячие"
            }
        }
    }

//    private val tabNumbers: Array<Int> = arrayOf(
//        R.drawable.baseline_latest,
//        R.drawable.baseline_best,
//        R.drawable.baseline_hot,
//    )

//    TabLayoutMediator(tabLayout, viewPager) { tab, position ->
//        tab.text = tabNames[position]
//
//
//        tab.setIcon(tabNumbers[position])
//
//        if (position == 2) {
//            val badge = tab.getOrCreateBadge()
//            badge.number = 1
//        }
//
//    }.attach()
}