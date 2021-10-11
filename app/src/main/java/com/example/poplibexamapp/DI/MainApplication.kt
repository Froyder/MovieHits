package com.example.poplibexamapp.DI

import com.example.poplibexamapp.GlideImageLoader
import com.github.terrakok.cicerone.Cicerone
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class MainApplication: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<MainApplication> =
        DaggerMainComponent.builder()
            .withContext(applicationContext)
            .apply {
                val cicerone = Cicerone.create()
                withRouter(cicerone.router)
                withNavigatorHolder(cicerone.getNavigatorHolder())
            }
            .withMainThread(AndroidSchedulers.mainThread())
            .withImageLoader(GlideImageLoader())
            .build()
}