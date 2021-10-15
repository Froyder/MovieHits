package com.example.poplibexamapp.DI

import android.content.Context
import androidx.room.Room
import com.example.poplibexamapp.MainRepository
import com.example.poplibexamapp.MainRepositoryInterface
import com.example.poplibexamapp.NetworkStatus
import com.example.poplibexamapp.database.LocalStorage
import com.example.poplibexamapp.database.MoviesCacheInterface
import com.example.poplibexamapp.netSource.DataEndPoints
import com.example.poplibexamapp.presentations.DetailsFragment
import com.example.poplibexamapp.presentations.ListFragment
import com.example.poplibexamapp.presentations.MainActivity
import com.example.poplibexamapp.presenters.MainPresenter

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module
interface MainModule {

    @ContributesAndroidInjector
    fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    fun bindMainPresenter(): MainPresenter

    @ContributesAndroidInjector
    fun bindListFragment(): ListFragment

    @ContributesAndroidInjector
    fun bindMainRepository(): MainRepositoryInterface

    @ContributesAndroidInjector
    fun bindDetailsFragment(): DetailsFragment

    @ContributesAndroidInjector
    fun bindMoviesCache(): MoviesCacheInterface

    companion object {
//        @Singleton
//        @Provides
//        fun provideMainRepository(
//            api: DataEndPoints,
//            dataBase: LocalStorage,
//            networkStatus: NetworkStatus
//        ): MainRepositoryInterface = MainRepository(api, dataBase, networkStatus)
    }

}