package com.example.poplibexamapp.data

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

data class ListOfItems(
    val results: List<ItemDataClass>
)

@Parcelize
data class ItemDataClass (
    val id: Int,
    val adult : Boolean,
    val overview: String,
    val budget: Int,
    val genre_name: String?,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Double,
    val vote_count: Int
) : Parcelable