package com.example.poplibexamapp.dagger

import com.example.poplibexamapp.presentations.DetailsFragment
import com.example.poplibexamapp.presentations.ListFragment
import com.example.poplibexamapp.presentations.MainActivity

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainModule {

    @ContributesAndroidInjector
    fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    fun bindListFragment(): ListFragment

    @ContributesAndroidInjector
    fun bindDetailsFragment(): DetailsFragment

}