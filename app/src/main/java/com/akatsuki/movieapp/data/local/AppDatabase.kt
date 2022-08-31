package com.akatsuki.movieapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.akatsuki.movieapp.models.local.SaveModel
import com.akatsuki.movieapp.models.local.UsersModel

@Database(entities = [UsersModel::class, SaveModel::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}