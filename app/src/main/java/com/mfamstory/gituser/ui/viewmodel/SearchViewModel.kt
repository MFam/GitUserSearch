package com.mfamstory.gituser.ui.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mfamstory.gituser.TAG
import com.mfamstory.gituser.database.dao.LikeUserDao
import com.mfamstory.gituser.database.entity.LikeUser
import com.mfamstory.gituser.network.GithubAPI
import com.mfamstory.gituser.network.model.User
import com.mfamstory.gituser.util.SingleLiveEvent
import com.mfamstory.gituser.util.ioThread
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors

class SearchViewModel(val api: GithubAPI, val dao: LikeUserDao) : DisposableViewModel() {

    private var et_query : String = ""

    private var _items : MutableLiveData<List<User>> = MutableLiveData()
    val items : MutableLiveData<List<User>> get() = _items

    private var _isLoading : MutableLiveData<Boolean> = MutableLiveData()
    val isLoading : MutableLiveData<Boolean> get() = _isLoading

    private val _hideKeyboard = SingleLiveEvent<Boolean>()
    val hideKeyboard : LiveData<Boolean> get() = _hideKeyboard

    private val _like = SingleLiveEvent<Boolean>()
    val like : LiveData<Boolean> get() = _like

    init {
        _items.value = arrayListOf()
    }

    @SuppressLint("CheckResult")
    fun doSearch() {
        if (et_query.isEmpty()) {
            return
        }

        _hideKeyboard.call()

        val params = mapOf(
            Pair("q", et_query),
            Pair("page", 1.toString()),
            Pair("per_page", 10.toString())
        )

        addDisposable(api.searchUser(params)
            .doOnSubscribe { _isLoading.postValue(true) }
            .doOnSuccess { _isLoading.postValue(false) }
            .doOnError { _isLoading.postValue(false) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( { res ->
                _items.value = res.users
            }, {
                    t -> t.printStackTrace()
            }))
    }

    fun onQueryChange(query : CharSequence) {
        this.et_query = query.toString()
    }

    fun onRefresh() {
        doSearch()
    }

    fun likeUser(user : User) {
        ioThread { dao.insert(LikeUser.to(user)) }
        _like.call()
    }
}