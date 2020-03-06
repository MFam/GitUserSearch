package com.mfamstory.gituser.paging

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.mfamstory.gituser.TAG
import com.mfamstory.gituser.network.GithubAPI
import com.mfamstory.gituser.network.model.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject

class NetworkUserDataSource(
    val query: String
) : PageKeyedDataSource<Int, User>(), KoinComponent {

    private var _isInitialLoading : MutableLiveData<Boolean> = MutableLiveData()
    val isInitialLoading : MutableLiveData<Boolean> get() = _isInitialLoading
    private var _networkStatusMsg : MutableLiveData<String> = MutableLiveData()
    val networkStatusMsg : MutableLiveData<String> get() = _networkStatusMsg
    private var _isEmpty : MutableLiveData<Boolean> = MutableLiveData()
    val isEmpty : MutableLiveData<Boolean> get() = _isEmpty

    @SuppressLint("CheckResult")
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, User>) {
        Log.d(TAG, "NetworkUserDataSource LoadInitial Start===")

        val curPage = 1
        val nextPage = curPage + 1
        val searchParams = mapOf(
            Pair("q", query),
            Pair("page", curPage.toString()),
            Pair("per_page", 10.toString())
        )

        val api : GithubAPI by inject()
        api.searchUser(searchParams)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isInitialLoading.postValue(true) }
            .doOnSuccess { isInitialLoading.postValue(false) }
            .subscribe({ res ->
                if (res.users.isEmpty()) {
                    _isEmpty.postValue(true)
                } else {
                    callback.onResult(res.users, null, nextPage)
                }
            }, {
                networkStatusMsg.postValue(it.message)
            })

//        isInitialLoading.postValue(true)
//        api.searchUser(searchParams).enqueue(
//            object : Callback<ResUsers> {
//                override fun onResponse(call: Call<ResUsers>, response: Response<ResUsers>) {
//                    isInitialLoading.postValue(false)
//                    if (response.isSuccessful) {
//                        val users = response.body()?.users
//                        if (users == null || users.size < 1) {
//                            isEmpty.postValue(true)
//                        } else {
//                            Log.d(TAG, "onResponse User size : ${users.size}")
//                            callback.onResult(users, null, nextPage)
//                        }
//                    } else {
//                        networkStatusMsg.postValue(response.errorBody()?.string() ?: "Unknown error")
//                    }
//                }
//                override fun onFailure(call: Call<ResUsers>, t: Throwable) {
//                    t.printStackTrace()
//                    isInitialLoading.postValue(false)
//                    networkStatusMsg.postValue(call.toString())
//                }
//            }
//        )
    }


    @SuppressLint("CheckResult")
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {
        Log.d(TAG, "NetworkUserDataSource loadAfter Start===")

        val searchParams = mapOf(
            Pair("q", query),
            Pair("page", params.key.toString()),
            Pair("per_page", params.requestedLoadSize.toString())
        )

        val api : GithubAPI by inject()
        api.searchUser(searchParams)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isInitialLoading.postValue(true) }
            .doOnSuccess { isInitialLoading.postValue(false) }
            .subscribe({ res ->
                val nextKey = params.key + 1
                callback.onResult(res.users, nextKey)
            }, {
                networkStatusMsg.postValue(it.message)
            })

//        api.searchUser(searchParams).enqueue(
//            object : Callback<ResUsers> {
//                override fun onResponse(call: Call<ResUsers>, response: Response<ResUsers>) {
//                    if (response.isSuccessful) {
//                        val users = response.body()?.users
//                        val nextKey = params.key + 1
//                        if (users != null && users.size > 0) {
//                            Log.d(TAG, "onResponse User size : ${users.size}")
//                            callback.onResult(users, nextKey)
//                        }
//                    } else {
//                        networkStatusMsg.postValue(response.errorBody()?.string() ?: "Unknown error")
//                    }
//                }
//                override fun onFailure(call: Call<ResUsers>, t: Throwable) {
//                    t.printStackTrace()
//                    networkStatusMsg.postValue(call.toString())
//                }
//            }
//        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {

    }
}