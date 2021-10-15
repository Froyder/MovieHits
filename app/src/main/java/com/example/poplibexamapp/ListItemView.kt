package com.example.poplibexamapp

import com.example.poplibexamapp.data.MovieDataClass

interface ListItemView: IListItemView {
        fun setMovieData(movieData: MovieDataClass)
        fun setPoster(url: String)
}