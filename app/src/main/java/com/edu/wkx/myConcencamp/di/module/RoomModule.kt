package com.edu.wkx.myConcencamp.di.module

import android.arch.persistence.room.Room
import android.content.Context
import com.edu.wkx.myConcencamp.repository.room.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context,
                AppDatabase::class.java, AppDatabase.TAG)
                .allowMainThreadQueries()
                .build()
    }
}