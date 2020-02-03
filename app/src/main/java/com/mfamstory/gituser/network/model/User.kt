package com.mfamstory.gituser.network.model

import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName("login") val login: String,
    @SerializedName("id") val id: String, // Int
    @SerializedName("node_id") val node_id: String, // Int
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("url") val url: String,
    @SerializedName("type") val type: String,
    @SerializedName("score") val score: String // Float
)