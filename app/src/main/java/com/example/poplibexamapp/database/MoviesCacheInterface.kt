package com.example.poplibexamapp.database

import com.example.poplibexamapp.data.MovieDataClass
import com.example.poplibexamapp.data.MoviesList
import io.reactivex.rxjava3.core.Single

interface MoviesCacheInterface {
    fun updateCachedList (moviesList: MoviesList)
    fun updateCachedMovie (movie: MovieDataClass)
    fun getCachedList() : Single<MoviesList>
    fun getCachedMovie (itemId: String) : Single<MovieDataClass>
}