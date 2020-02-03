package com.mfamstory.gituser.database.dao

import androidx.paging.DataSource
import androidx.room.*
import com.mfamstory.gituser.database.entity.LikeUser
import io.reactivex.disposables.Disposable

@Dao
interface LikeUserDao {

    @Query("SELECT * FROM like_user ORDER BY created ASC")
    fun findAll(): DataSource.Factory<Int, LikeUser>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: LikeUser)

    @Delete
    fun delete(user: LikeUser)

}