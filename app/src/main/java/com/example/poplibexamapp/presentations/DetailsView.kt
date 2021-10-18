package com.example.poplibexamapp.presentations

import com.example.poplibexamapp.model.MovieDataClass
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface DetailsView: MvpView {

    fun setDetails(movieData : MovieDataClass)
    fun onError(throwable: Throwable)
}