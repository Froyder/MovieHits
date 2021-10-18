package com.example.poplibexamapp

import com.example.poplibexamapp.model.MovieDataClass

interface ListItemView: IListItemView {
        fun setMovieData(movieData: MovieDataClass)
        fun setPoster(url: String)
}