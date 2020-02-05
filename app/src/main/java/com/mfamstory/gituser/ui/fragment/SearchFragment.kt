package com.mfamstory.gituser.ui.fragment

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.mfamstory.gituser.R
import com.mfamstory.gituser.databinding.FragmentSearchBinding
import com.mfamstory.gituser.ui.viewmodel.SearchViewModel
import com.mfamstory.gituser.util.hideKeyboard
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.android.viewmodel.ext.android.getViewModel

class SearchFragment : BindingFragment<FragmentSearchBinding>() {

    override fun getLayoutResId() = R.layout.fragment_search

    companion object {
        @Volatile private var instance: SearchFragment? = null
        @JvmStatic
        fun getInstance(): SearchFragment = instance ?: synchronized(this) {
            instance ?: SearchFragment().also {
                instance = it
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val vm : SearchViewModel = getViewModel()
        binding.vm = vm
        binding.lifecycleOwner = this.viewLifecycleOwner

        vm.hideKeyboard.observe(this.viewLifecycleOwner, Observer {
            hideKeyboard()
        })

        vm.like.observe(this.viewLifecycleOwner, Observer {
            Snackbar.make(view, "Like!", Snackbar.LENGTH_SHORT).show()
        })

        et_query.setOnEditorActionListener { _, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> vm.doSearch()
            }
            false
        }
    }
}