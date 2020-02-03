package com.mfamstory.gituser.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.mfamstory.gituser.database.DateConverter
import com.mfamstory.gituser.network.model.User
import java.util.*

@Entity(tableName = "like_user")
@TypeConverters(DateConverter::class)
data class LikeUser(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "login") val login: String,
    @ColumnInfo(name = "avatar_url") val avatar_url: String,
    @ColumnInfo(name = "git_id") val git_id: String,
    @ColumnInfo(name = "created") val created: Date
) {
    companion object {
        fun to(user: User): LikeUser {
            return LikeUser(
                login = user.login,
                avatar_url = user.avatarUrl,
                git_id = user.id,
                created = Date()
            )
        }
    }
}