package com.example.poplibexamapp.presenters

import dagger.assisted.AssistedFactory

@AssistedFactory
interface DetailsPresentersFactory {

    fun createDetailsPresenter (itemID: String): DetailsPresenter

}