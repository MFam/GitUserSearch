package com.mfamstory.rxmvvmtest.di

import com.mfamstory.gituser.ui.viewmodel.LikeViewModel
import com.mfamstory.gituser.ui.viewmodel.MainViewModel
import com.mfamstory.gituser.ui.viewmodel.SearchViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { LikeViewModel(get()) }
    viewModel { SearchViewModel(get(), get()) }
}