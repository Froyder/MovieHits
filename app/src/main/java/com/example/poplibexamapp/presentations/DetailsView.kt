package com.example.poplibexamapp.presentations

import com.example.poplibexamapp.data.ItemDataClass
import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState

interface DetailsView: MvpView {

    @SingleState
    fun setDetails(itemData : ItemDataClass)
}