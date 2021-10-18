package com.example.poplibexamapp.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class MoviesList(
    val results : List<MovieDataClass>
)

@Entity(tableName = "movie")
@Parcelize
data class MovieDataClass (
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String,
    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    val poster_path: String,
    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    val overview: String,
    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    val release_date: String,
    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    val vote_average: Double,

    val genre_name: String?,
    val vote_count: Int,
    val status: String?
) : Parcelable
