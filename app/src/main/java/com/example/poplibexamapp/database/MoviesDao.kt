package com.example.poplibexamapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.poplibexamapp.model.MovieDataClass

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movie WHERE status LIKE :status")
    fun getMoviesListFromDB(status: String): List<MovieDataClass>

    @Query("SELECT * FROM movie WHERE id LIKE :id LIMIT 1")
    fun getMovieByIDFromDB(id: String): MovieDataClass

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateList(moviesList: List<MovieDataClass>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateMovie (movie: MovieDataClass)

}