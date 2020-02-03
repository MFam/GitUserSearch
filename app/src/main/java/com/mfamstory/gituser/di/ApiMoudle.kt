package com.mfamstory.rxmvvmtest.di

import com.mfamstory.gituser.network.GithubAPI
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single (createdAtStart =  false) { get<Retrofit>().create(GithubAPI::class.java) }
}