package com.arch.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arch.database.schema.dao.WatchMediaDao
import com.arch.database.schema.model.WatchMediaEntity

@Database(entities = [
    WatchMediaEntity::class
], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun watchMediaDao(): WatchMediaDao
}