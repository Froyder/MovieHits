package com.example.poplibexamapp.database

import com.example.poplibexamapp.model.MovieDataClass
import com.example.poplibexamapp.model.MoviesList
import io.reactivex.rxjava3.core.Single

private const val TOP_RATED = "top_rated"

class TopMoviesCache (private val dataBase: LocalStorage): TopMoviesCacheInterface {

    override fun updateCachedList(moviesList: MoviesList) {
        val roomList = moviesList.results.map { movie ->
            MovieDataClass(movie.id, movie.title, movie.poster_path,
                movie.overview, movie.release_date, movie.vote_average,
                movie.genre_name, movie.vote_count, TOP_RATED
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
            dataBase.moviesDao().getMoviesListFromDB(TOP_RATED).map { movie ->
                MovieDataClass(movie.id, movie.title, movie.poster_path, movie.overview,
                    movie.release_date,movie.vote_average, movie.genre_name,movie.vote_count, TOP_RATED
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