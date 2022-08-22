package com.don.library.core.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class BaseTabAdapter(fm: FragmentManager, fragments: MutableList<Fragment>, vararg titles: String) :
    FragmentPagerAdapter(fm) {

    private var mFragments = fragments
    private var mTitles = titles

    override fun getItem(position: Int): Fragment {
        return mFragments[position]
    }

    override fun getCount(): Int {
        return mFragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mTitles[position]
    }
}