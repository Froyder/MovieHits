package com.example.poplibexamapp

import android.content.SharedPreferences
import javax.inject.Inject

private const val POPULAR = "POPULAR"
private const val HEADER_TOP = "Top rated movies:"
private const val HEADER_POP = "Now in theatres:"

class SettingsProvider @Inject constructor(private val sharedPreferences: SharedPreferences): SettingsProviderInterface {

    override fun headerSettings(): String {
        return if (getListSettings() == POPULAR) HEADER_POP else HEADER_TOP
    }

    override fun saveListSettings(listToShow: String) {
        sharedPreferences.edit().putString("LIST_TO_SHOW", listToShow).apply()
    }

    override fun getListSettings(): String {
        return sharedPreferences.getString("LIST_TO_SHOW", POPULAR) ?: POPULAR
    }

}