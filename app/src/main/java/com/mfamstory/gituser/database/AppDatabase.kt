package com.mfamstory.gituser.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mfamstory.gituser.database.AppDatabase.Companion.DB_VERSION
import com.mfamstory.gituser.database.dao.LikeUserDao
import com.mfamstory.gituser.database.entity.LikeUser

@Database(entities = [LikeUser::class], version = DB_VERSION, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getLikeUserDao(): LikeUserDao

    companion object {
        const val DB_VERSION = 1
        private const val DB_NAME = "database.db"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase = INSTANCE ?: synchronized(this) {
                INSTANCE ?: build(context).also { INSTANCE = it }
            }

        private fun build(context: Context) =
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME).build()
    }
}