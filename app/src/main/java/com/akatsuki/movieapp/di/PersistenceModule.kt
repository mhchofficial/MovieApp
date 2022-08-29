package com.akatsuki.movieapp.di

import android.content.Context
import androidx.room.Room
import com.akatsuki.movieapp.data.local.AppDatabase
import com.akatsuki.movieapp.data.local.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext application: Context): AppDatabase {
        return Room
            .databaseBuilder(application, AppDatabase::class.java, "movie_users")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideBookmarkDao(bookmarkDatabase: AppDatabase) : UserDao {
        return bookmarkDatabase.userDao()
    }
}