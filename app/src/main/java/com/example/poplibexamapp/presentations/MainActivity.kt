package com.example.poplibexamapp.presentations

import android.os.Bundle
import android.widget.Toast
import com.example.poplibexamapp.CustomSchedulersInterface
import com.example.poplibexamapp.NetworkStatus
import com.example.poplibexamapp.presenters.MainPresenter
import com.example.poplibexamapp.R
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
    lateinit var customSchedulers: CustomSchedulersInterface

    private val presenter by moxyPresenter { MainPresenter (router, customSchedulers) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(this, networkStatus.netStatusSubject.value.toString(), Toast.LENGTH_LONG).show()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }
}