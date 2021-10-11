package com.example.poplibexamapp

import com.example.poplibexamapp.data.ItemDataClass
import com.example.poplibexamapp.data.ListOfItems
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface DataEndPoints {

    @GET("/3/movie/popular/")
    fun getListItems(): Single<ListOfItems>

    @GET("/3/movie/{movie_id}")
    fun getItemByID (@Path("movie_id") itemID: String): Single<ItemDataClass>

}