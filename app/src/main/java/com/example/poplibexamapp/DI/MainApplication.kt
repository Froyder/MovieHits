package com.example.poplibexamapp.DI

import com.example.poplibexamapp.CustomSchedulers
import com.example.poplibexamapp.GlideImageLoader
import com.example.poplibexamapp.NetworkStatus
import com.github.terrakok.cicerone.Cicerone
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MainApplication: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<MainApplication> =
        DaggerMainComponent.builder()
            .withContext(applicationContext)
            .apply {
                val cicerone = Cicerone.create()
                withRouter(cicerone.router)
                withNavigatorHolder(cicerone.getNavigatorHolder())
            }
            .withSchedulers(CustomSchedulers())
            .withImageLoader(GlideImageLoader())
            .withNetworkStatus(NetworkStatus(applicationContext))
            .build()
}