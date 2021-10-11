package com.example.poplibexamapp

import com.example.poplibexamapp.data.ItemDataClass
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class NetSourceRepository(val api: DataEndPoints): MainRepositoryInterface {
    override fun getItemsList() = api.getListItems().subscribeOn(Schedulers.io())
    override fun getItemByID(itemId: String) = api.getItemByID(itemId).subscribeOn(Schedulers.io())
}