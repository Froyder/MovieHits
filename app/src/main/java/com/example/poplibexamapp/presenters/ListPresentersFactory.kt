package com.example.poplibexamapp.presenters

import dagger.assisted.AssistedFactory

@AssistedFactory
interface ListPresentersFactory {

    fun createListPresenter (): ListPresenter

}