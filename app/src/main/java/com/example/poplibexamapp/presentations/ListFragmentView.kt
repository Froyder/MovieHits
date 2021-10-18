package com.example.poplibexamapp.presentations

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.AddToEnd

@StateStrategyType(AddToEndSingleStrategy::class)
interface ListFragmentView: MvpView {
    fun initRVList()
    fun setList (headerText: String)
    fun onError(throwable: Throwable)
}