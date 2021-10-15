package com.example.poplibexamapp.netSource

import com.example.poplibexamapp.data.MovieDataClass
import com.example.poplibexamapp.data.MoviesList
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface DataEndPoints {

    @GET("/3/movie/popular/")
    fun getPopList(): Single<MoviesList>

    @GET("/3/movie/top_rated/")
    fun getTopList(): Single<MoviesList>

    @GET("/3/movie/{movie_id}")
    fun getItemByID (@Path("movie_id") itemID: String): Single<MovieDataClass>

}