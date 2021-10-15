package com.example.poplibexamapp

import com.example.poplibexamapp.database.MoviesCacheInterface
import com.example.poplibexamapp.netSource.DataEndPoints
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val api: DataEndPoints,
    private val networkStatus: NetworkStatus,
    private val moviesCache: MoviesCacheInterface,
    private val ioScheduler : Scheduler
    ): MainRepositoryInterface {

    override fun getMoviesList() = networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getListItems()
                    .flatMap { moviesList ->
                        Single.fromCallable {
                            moviesCache.updateCachedList(moviesList)
                            moviesList
                        }
                    }
            } else {
                moviesCache.getCachedList()
            }
        }.subscribeOn(ioScheduler)

    override fun getMovieByID(itemId: String) = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getItemByID(itemId)
                .flatMap { movie ->
                    Single.fromCallable {
                        moviesCache.updateCachedMovie(movie)
                        movie
                    }
                }
        } else {
            moviesCache.getCachedMovie(itemId)
        }
    }.subscribeOn(ioScheduler)
}