package com.example.poplibexamapp.presentations

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen

class ListScreen(private val someText : String): FragmentScreen {

    override fun createFragment(factory: FragmentFactory): Fragment =
        ListFragment.newInstance(someText)
}