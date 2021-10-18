package com.example.poplibexamapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.poplibexamapp.model.MovieDataClass

@Database(exportSchema = false, entities = [MovieDataClass::class], version = 2)
abstract class LocalStorage: RoomDatabase() {

    abstract fun moviesDao() : MoviesDao

}