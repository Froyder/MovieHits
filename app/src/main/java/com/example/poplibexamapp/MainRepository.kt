package com.example.poplibexamapp

import android.view.ViewDebug
import com.example.poplibexamapp.data.MovieDataClass
import com.example.poplibexamapp.data.MoviesList
import com.example.poplibexamapp.database.LocalStorage
import com.example.poplibexamapp.netSource.DataEndPoints
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val api: DataEndPoints,
    private val dataBase: LocalStorage,
    private val networkStatus: NetworkStatus
    //private val customSchedulers: CustomSchedulersInterface
    ): MainRepositoryInterface {

    //override fun getMoviesList(): Single<MoviesList> = api.getListItems().subscribeOn(Schedulers.io())

    override fun getMoviesList() = networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getListItems()
                    .flatMap { moviesList ->
                        Single.fromCallable {
                            val roomList = moviesList.results.map { movie ->
                                MovieDataClass(
                                    movie.id,
                                    movie.title,
                                    movie.poster_path,
                                    movie.overview,
                                    movie.release_date,
                                    movie.genre_name,
                                    movie.vote_average,
                                    movie.vote_count
                                )
                            }
                            dataBase.moviesDao().updateList(roomList)
                            moviesList
                        }
                    }
            } else {
                getCachedData()
            }
        }.subscribeOn(Schedulers.io())

    private fun getCachedData(): Single<MoviesList> {
        return Single.fromCallable {
            MoviesList (results =
            dataBase.moviesDao().getMoviesListFromDB().map { movie ->
                MovieDataClass(
                    movie.id,
                    movie.title,
                    movie.poster_path,
                    movie.overview,
                    movie.release_date,
                    movie.genre_name,
                    movie.vote_average,
                    movie.vote_count
                )
            })
        }
    }

  //     override fun getMovieByID(itemId: Int) = api.getItemByID(itemId.toString()).subscribeOn(Schedulers.io())

    override fun getMovieByID(itemId: String) = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getItemByID(itemId)
                .flatMap { movie ->
                    Single.fromCallable {
                        dataBase.moviesDao().updateMovie(movie)
                        movie
                    }
                }
        } else {
            getMovieCachedData(itemId)
        }
    }.subscribeOn(Schedulers.io())

    private fun getMovieCachedData(itemId: String): Single<MovieDataClass> {
        return Single.fromCallable {
            dataBase.moviesDao().getMovieByIDFromDB(itemId)
            }
    }
}