package com.example.poplibexamapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.poplibexamapp.data.MovieDataClass

@Database(exportSchema = false, entities = [MovieDataClass::class], version = 1)
abstract class LocalStorage: RoomDatabase() {

    abstract fun moviesDao() : MoviesDao

}