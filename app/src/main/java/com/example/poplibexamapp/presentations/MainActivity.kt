package com.example.poplibexamapp.presentations

import com.example.poplibexamapp.BackButtonListener
import com.example.poplibexamapp.netSource.NetworkStatus
import com.example.poplibexamapp.presenters.MainPresenter
import com.example.poplibexamapp.R
import com.example.poplibexamapp.database.LocalStorage
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class MainActivity : MvpDIActivity(R.layout.activity_main), MainView {

    private val navigator = AppNavigator(this, R.id.container)

    @Inject
    lateinit var router: Router
    @Inject
    lateinit var navigatorHolder: NavigatorHolder
    @Inject
    lateinit var networkStatus: NetworkStatus
    @Inject
    lateinit var dataBase: LocalStorage

    private val presenter by moxyPresenter { MainPresenter (router) }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if(it is BackButtonListener && it.backPressed()){
                return
            }
        }
        presenter.backClicked()
    }
}