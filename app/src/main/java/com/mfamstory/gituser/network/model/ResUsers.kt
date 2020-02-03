package com.mfamstory.gituser.network.model

import com.google.gson.annotations.SerializedName

data class ResUsers (
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("incomplete_results") val incompleteResults: Boolean,
    @SerializedName("items") val users: ArrayList<User>
)