package com.mfamstory.gituser.ui.adapter

import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

@BindingAdapter(value = ["fragmentManager"])
fun setAdapter(viewPager: ViewPager, fm: FragmentManager) {
    viewPager.adapter.let {
        viewPager.adapter = SectionsPagerAdapter(viewPager.context, fm)
    }
}

@BindingAdapter(value = ["viewPager"])
fun setupViewPager(tabLayout: TabLayout, viewPager: ViewPager) {
    tabLayout.setupWithViewPager(viewPager)
}
