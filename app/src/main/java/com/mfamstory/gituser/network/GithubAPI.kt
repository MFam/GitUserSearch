package com.mfamstory.gituser.network

import com.mfamstory.gituser.network.model.ResUsers
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap


interface GithubAPI {
    // params is 'q', 'sort', 'order' &page=1&per_page=10
    @GET("/search/users")
    fun searchUser(@QueryMap params: Map<String, String>): Single<ResUsers>
}