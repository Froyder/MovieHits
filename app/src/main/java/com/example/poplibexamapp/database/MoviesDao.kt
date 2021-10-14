package com.example.poplibexamapp.database

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.poplibexamapp.data.MovieDataClass

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movie")
    fun getMoviesListFromDB(): List<MovieDataClass>

    @Query("SELECT * FROM movie WHERE id LIKE :id LIMIT 1")
    fun getMovieByIDFromDB(id: String): MovieDataClass

    @Insert(onConflict = REPLACE)
    fun updateList(moviesList: List<MovieDataClass>)

    @Insert(onConflict = REPLACE)
    fun updateMovie (movie: MovieDataClass)

}