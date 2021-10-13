package com.example.poplibexamapp

import com.example.poplibexamapp.netSource.DataEndPoints

class NetSourceRepository(
    private val api: DataEndPoints,
    private val customSchedulers: CustomSchedulersInterface
    ): MainRepositoryInterface {
        override fun getItemsList() = api.getListItems().subscribeOn(customSchedulers.background())
        override fun getItemByID(itemId: String) = api.getItemByID(itemId).subscribeOn(customSchedulers.background())
}