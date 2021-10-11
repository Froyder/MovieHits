package com.example.poplibexamapp.presentations

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen

class DetailsScreen(private val itemID : String): FragmentScreen {

    override fun createFragment(factory: FragmentFactory): Fragment =
        DetailsFragment.newInstance(itemID)
}