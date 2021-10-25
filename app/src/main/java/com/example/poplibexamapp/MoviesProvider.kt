package com.example.poplibexamapp

import android.content.SharedPreferences
import com.example.poplibexamapp.database.PopularMoviesCacheInterface
import com.example.poplibexamapp.database.TopMoviesCacheInterface
import com.example.poplibexamapp.model.MovieDataClass
import com.example.poplibexamapp.model.MoviesList
import com.example.poplibexamapp.netSource.ApiHolder
import com.example.poplibexamapp.netSource.NetworkStatusInterface
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

private const val POPULAR = "POPULAR"

class MoviesProvider @Inject constructor(
    private val settingsProvider: SettingsProviderInterface,
    private val apiHolder: ApiHolder,
    private val networkStatus: NetworkStatusInterface,
    private val topMoviesCache: TopMoviesCacheInterface,
    private val popularMoviesCache: PopularMoviesCacheInterface,
    private val ioScheduler : Scheduler
    ): MoviesProviderInterface {

    override fun getMoviesList(): Single<MoviesList> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                if (settingsProvider.getListSettings() == POPULAR) {
                    apiHolder.api.getPopList()
                        .flatMap { moviesList ->
                            Single.fromCallable {
                                popularMoviesCache.updateCachedList(moviesList)
                                moviesList
                            }
                        }
                } else {
                    apiHolder.api.getTopList()
                        .flatMap { moviesList ->
                            Single.fromCallable {
                                topMoviesCache.updateCachedList(moviesList)
                                moviesList
                            }
                        }
                }
            } else {
                if (settingsProvider.getListSettings() == POPULAR) popularMoviesCache.getCachedList() else topMoviesCache.getCachedList()
            }
        }.subscribeOn(ioScheduler)

    override fun getMovieByID(itemId: String): Single<MovieDataClass> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                apiHolder.api.getItemByID(itemId)
                    .flatMap { movie ->
                        Single.fromCallable {
                            popularMoviesCache.updateCachedMovie(movie)
                            movie
                        }
                    }
            } else {
                popularMoviesCache.getCachedMovie(itemId)
            }
        }.subscribeOn(ioScheduler)
}