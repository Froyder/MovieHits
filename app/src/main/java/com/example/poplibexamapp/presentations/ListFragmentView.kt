package com.example.poplibexamapp.presentations

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ListFragmentView: MvpView {
    fun initRVList()
    fun setList ()
    fun onBackClicked()
}