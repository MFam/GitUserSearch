package com.mfamstory.gituser.network.model

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

data class UserSearchResult(
    val data: LiveData<PagedList<User>>,
    val isInitialLoading : LiveData<Boolean>,
    val networkErrors: LiveData<String>,
    val isEmpty : LiveData<Boolean>
)