package com.example.poplibexamapp.database

import com.example.poplibexamapp.model.MovieDataClass
import com.example.poplibexamapp.model.MoviesList
import io.reactivex.rxjava3.core.Single

interface TopMoviesCacheInterface {
    fun updateCachedList (moviesList: MoviesList)
    fun updateCachedMovie (movie: MovieDataClass)
    fun getCachedList() : Single<MoviesList>
    fun getCachedMovie (itemId: String) : Single<MovieDataClass>
}