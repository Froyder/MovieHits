package com.example.poplibexamapp.database

import com.example.poplibexamapp.model.MovieDataClass
import com.example.poplibexamapp.model.MoviesList
import io.reactivex.rxjava3.core.Single

private const val POPULAR = "popular"

class PopularMoviesCache (private val dataBase: LocalStorage): PopularMoviesCacheInterface {

    override fun updateCachedList(moviesList: MoviesList) {
        val roomList = moviesList.results.map { movie ->
            MovieDataClass(movie.id, movie.title, movie.poster_path,
                movie.overview, movie.release_date, movie.vote_average,
                movie.genre_name, movie.vote_count, POPULAR
            )
        }
        dataBase.moviesDao().updateList(roomList)
    }

    override fun updateCachedMovie(movie: MovieDataClass) {
        dataBase.moviesDao().updateMovie(movie)
    }

    override fun getCachedList(): Single<MoviesList> {
        return Single.fromCallable {
            MoviesList (results =
            dataBase.moviesDao().getMoviesListFromDB(POPULAR).map { movie ->
                MovieDataClass(movie.id, movie.title, movie.poster_path, movie.overview,
                    movie.release_date,movie.vote_average, movie.genre_name,movie.vote_count, POPULAR
                )
            })
        }
    }

    override fun getCachedMovie(itemId: String): Single<MovieDataClass> {
        return Single.fromCallable {
            dataBase.moviesDao().getMovieByIDFromDB(itemId)
        }
    }
}