package com.example.poplibexamapp.presentations

import android.content.Context
import androidx.annotation.LayoutRes
import com.example.poplibexamapp.GlideImageLoader
import com.example.poplibexamapp.MainRepository
import com.example.poplibexamapp.NetworkStatus
import com.example.poplibexamapp.database.LocalStorage
import com.example.poplibexamapp.database.MoviesCacheInterface
import com.example.poplibexamapp.netSource.ApiHolder
import com.example.poplibexamapp.netSource.DataEndPoints
import com.github.terrakok.cicerone.Router
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpAppCompatFragment
import javax.inject.Inject

abstract class MvpDIFragment(@LayoutRes contentLayoutId: Int) : MvpAppCompatFragment(contentLayoutId),
    HasAndroidInjector {

    @Inject
    lateinit var router: Router
    @Inject
    lateinit var ioScheduler: Scheduler
    @Inject
    lateinit var imageLoader: GlideImageLoader
    @Inject
    lateinit var networkStatus: NetworkStatus
    @Inject
    lateinit var dataBase: LocalStorage
    @Inject
    lateinit var moviesCache: MoviesCacheInterface
    @Inject
    lateinit var apiHolder: ApiHolder
    @Inject
    lateinit var mainRepository: MainRepository

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

}