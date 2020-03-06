package com.mfamstory.gituser.paging

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.mfamstory.gituser.TAG
import com.mfamstory.gituser.network.model.User

class NetworkUserDataFactory(
    val query: String
) : DataSource.Factory<Int, User>() {

    private val mutableLiveData: MutableLiveData<NetworkUserDataSource> = MutableLiveData<NetworkUserDataSource>()
    lateinit var dataSource : NetworkUserDataSource

    override fun create(): DataSource<Int, User> {
        Log.d(TAG, "NetworkUserDataFactory create()==")
        dataSource = NetworkUserDataSource(query)
        mutableLiveData.postValue(dataSource)
        return dataSource
    }

    fun getMutableLiveData() = mutableLiveData
}