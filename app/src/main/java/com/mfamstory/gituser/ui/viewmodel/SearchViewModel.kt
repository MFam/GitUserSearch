package com.mfamstory.gituser.ui.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.mfamstory.gituser.TAG
import com.mfamstory.gituser.database.dao.LikeUserDao
import com.mfamstory.gituser.database.entity.LikeUser
import com.mfamstory.gituser.network.model.User
import com.mfamstory.gituser.network.model.UserSearchResult
import com.mfamstory.gituser.paging.NetworkUserDataFactory
import com.mfamstory.gituser.util.SingleLiveEvent
import com.mfamstory.gituser.util.ioThread
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class SearchViewModel(val dao: LikeUserDao) : DisposableViewModel() {

    private var etQuery : String = ""
    private val query : MutableLiveData<String> = MutableLiveData()

    private val userSearchResult = Transformations.map(query, { requestUserSearch() })

    var items = Transformations.switchMap(userSearchResult, { it.data })

    var isLoading = Transformations.switchMap(userSearchResult, { it.isInitialLoading })

    var isDataEmpty = Transformations.switchMap(userSearchResult, { it.isEmpty })

    var isNetworkError = Transformations.switchMap(userSearchResult, { it.networkErrors })

    private val _hideKeyboard = SingleLiveEvent<Boolean>()
    val hideKeyboard : LiveData<Boolean> get() = _hideKeyboard

    private val _like = SingleLiveEvent<Boolean>()
    val like : LiveData<Boolean> get() = _like

    private val executor : Executor = Executors.newFixedThreadPool(5)
    private val PAGE_SIZE = 10


    @SuppressLint("CheckResult")
    fun doSearch() {
        Log.d(TAG, "doSearch===")
        if (etQuery.isEmpty()) {
            return
        }

        _hideKeyboard.call()

        query.postValue(etQuery)
    }

    fun requestUserSearch() : UserSearchResult {
        val dataFactory = NetworkUserDataFactory(etQuery)

        val pagedListConfig = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setInitialLoadSizeHint(PAGE_SIZE * 3) // default: page size * 3
            .setPrefetchDistance(PAGE_SIZE) // default: page size
            .setEnablePlaceholders(false) // default: true
            .build()

        val data = LivePagedListBuilder(dataFactory, pagedListConfig)
            .setFetchExecutor(executor) // Thread : defaults to the Arch components I/O thread pool.
            .build()

        val initialLoading = Transformations.switchMap(dataFactory.getMutableLiveData(), {dataSource -> dataSource.isInitialLoading})
        val networkError = Transformations.switchMap(dataFactory.getMutableLiveData(), {dataSource -> dataSource.networkStatusMsg})
        val empty = Transformations.switchMap(dataFactory.getMutableLiveData(), {dataSource -> dataSource.isEmpty})

        return UserSearchResult(data, initialLoading, networkError, empty)
    }

    fun onQueryChange(query : CharSequence) {
        this.etQuery = query.toString()
    }

    fun onRefresh() {
        doSearch()
    }

    fun likeUser(user : User) {
        ioThread { dao.insert(LikeUser.to(user)) }
        _like.call()
    }
}
