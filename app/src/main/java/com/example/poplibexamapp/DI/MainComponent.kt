package com.example.poplibexamapp.DI

import android.content.Context
import com.example.poplibexamapp.GlideImageLoader
import com.example.poplibexamapp.ImageLoaderInterface
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import io.reactivex.rxjava3.core.Scheduler

@Component (modules = [AndroidInjectionModule::class, MainModule::class])
interface MainComponent: AndroidInjector<MainApplication> {

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun withContext(context: Context): Builder

        @BindsInstance
        fun withRouter(router: Router): Builder

        @BindsInstance
        fun withNavigatorHolder(navigatorHolder: NavigatorHolder): Builder

        @BindsInstance
        fun withMainThread (schedulers: Scheduler): Builder

        @BindsInstance
        fun withImageLoader (imageLoader: GlideImageLoader): Builder

        fun build(): MainComponent
    }
}