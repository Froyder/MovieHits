package com.example.poplibexamapp

import com.example.poplibexamapp.data.ItemDataClass
import com.example.poplibexamapp.data.ListOfItems
import io.reactivex.Single

interface  MainRepositoryInterface {
    fun getItemsList(): Single<ListOfItems>
    fun getItemByID(itemId: String): Single<ItemDataClass>
}