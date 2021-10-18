package com.example.poplibexamapp.dagger

import com.example.poplibexamapp.MoviesProviderInterface
import com.example.poplibexamapp.database.PopularMoviesCacheInterface
import com.example.poplibexamapp.presentations.DetailsFragment
import com.example.poplibexamapp.presentations.ListFragment
import com.example.poplibexamapp.presentations.MainActivity
import com.example.poplibexamapp.presenters.MainPresenter

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainModule {

    @ContributesAndroidInjector
    fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    fun bindMainPresenter(): MainPresenter

    @ContributesAndroidInjector
    fun bindListFragment(): ListFragment

    @ContributesAndroidInjector
    fun bindMainRepository(): MoviesProviderInterface

    @ContributesAndroidInjector
    fun bindDetailsFragment(): DetailsFragment

}