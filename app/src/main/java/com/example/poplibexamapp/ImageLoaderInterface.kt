package com.example.poplibexamapp

interface ImageLoaderInterface <T> {
    fun loadInto(url: String, container: T)
}