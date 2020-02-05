package com.mfamstory.gituser.ui.activity

import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.mfamstory.gituser.R
import com.mfamstory.gituser.databinding.ActivityMainBinding
import com.mfamstory.gituser.ui.adapter.SectionsPagerAdapter
import com.mfamstory.gituser.ui.viewmodel.MainViewModel
import com.mfamstory.gituser.util.hideKeyboard
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.getViewModel

class MainActivity : BindingActivity<ActivityMainBinding>() {

    override fun getLayoutResId() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = getViewModel()
        binding.lifecycleOwner = this
        binding.fm = supportFragmentManager

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) = hideKeyboard()
            override fun onPageSelected(position: Int) = hideKeyboard()
        })
    }
}