package com.example.poplibexamapp

import com.example.poplibexamapp.model.MovieDataClass
import com.example.poplibexamapp.model.MoviesList
import io.reactivex.rxjava3.core.Single

interface  MoviesProviderInterface {
    fun getMoviesList(listToShow: String): Single<MoviesList>
    fun getMovieByID(itemId: String): Single<MovieDataClass>
}