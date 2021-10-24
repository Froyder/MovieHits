package com.example.poplibexamapp

interface SettingsProviderInterface {
    fun saveListSettings (listToShow : String)
    fun getListSettings(): String
    fun headerSettings(): String
}