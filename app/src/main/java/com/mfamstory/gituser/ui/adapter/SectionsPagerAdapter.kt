package com.mfamstory.gituser.ui.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.mfamstory.gituser.R
import com.mfamstory.gituser.ui.fragment.LikeFragment
import com.mfamstory.gituser.ui.fragment.SearchFragment

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val PAGE_COUNT = 2
    private val TAB_TITLES = arrayOf(
        R.string.search,
        R.string.like
    )

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return SearchFragment.getInstance()
            1 -> return LikeFragment.getInstance()
        }
        return SearchFragment.getInstance()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return PAGE_COUNT
    }
}