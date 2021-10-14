package com.example.poplibexamapp.DI

import android.content.Context
import androidx.room.Room
import com.example.poplibexamapp.database.LocalStorage
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface DataBaseModule {

    companion object {
        @Singleton
        @Provides
        fun provideDataBase(context: Context): LocalStorage =
            Room.databaseBuilder(context, LocalStorage::class.java, "movies.db")
                .build()
    }

}