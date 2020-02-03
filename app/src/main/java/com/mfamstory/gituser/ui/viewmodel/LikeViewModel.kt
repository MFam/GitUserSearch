package com.mfamstory.gituser.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.mfamstory.gituser.database.dao.LikeUserDao
import com.mfamstory.gituser.database.entity.LikeUser
import com.mfamstory.gituser.util.ioThread

class LikeViewModel(val dao: LikeUserDao) : DisposableViewModel() {

    val items: LiveData<PagedList<LikeUser>> = LivePagedListBuilder(dao.findAll(),10).build()

    fun removeUser(user : LikeUser) {
        ioThread { dao.delete(user) }
    }
}