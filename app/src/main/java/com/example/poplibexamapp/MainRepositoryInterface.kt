package com.example.poplibexamapp

import com.example.poplibexamapp.data.MovieDataClass
import com.example.poplibexamapp.data.MoviesList
import io.reactivex.rxjava3.core.Single

interface  MainRepositoryInterface {
    fun getMoviesList(): Single<MoviesList>
    fun getMovieByID(itemId: String): Single<MovieDataClass>
}