package com.example.poplibexamapp.DI

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
    fun bindDetailsFragment(): DetailsFragment

}