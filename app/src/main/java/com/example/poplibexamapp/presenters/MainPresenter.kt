package com.example.poplibexamapp.presenters

import com.example.poplibexamapp.presentations.ListScreen
import com.example.poplibexamapp.presentations.MainView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class MainPresenter(private val router: Router) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        router.navigateTo(ListScreen("LIST SCREEN"))
    }

}