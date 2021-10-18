package com.example.poplibexamapp.dagger

import android.content.Context
import androidx.room.Room
import com.example.poplibexamapp.MoviesProvider
import com.example.poplibexamapp.MoviesProviderInterface
import com.example.poplibexamapp.netSource.NetworkStatus
import com.example.poplibexamapp.database.*
import com.example.poplibexamapp.netSource.ApiHolder
import com.example.poplibexamapp.netSource.NetworkStatusInterface
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Singleton

@Module
interface DataBaseModule {

    companion object {
        @Singleton
        @Provides
        fun provideDataBase(context: Context): LocalStorage =
            Room.databaseBuilder(context, LocalStorage::class.java, "movies.db")
                .build()

        @Singleton
        @Provides
        fun providePopularMoviesCache (dataBase: LocalStorage)
            : PopularMoviesCacheInterface = PopularMoviesCache(dataBase)

        @Singleton
        @Provides
        fun provideTopMoviesCache (dataBase: LocalStorage)
            : TopMoviesCacheInterface = TopMoviesCache(dataBase)

        @Singleton
        @Provides
        fun provideMoviesApi(): ApiHolder = ApiHolder()

        @Singleton
        @Provides
        fun provideMovieProvider (apiHolder: ApiHolder,
                                  networkStatus: NetworkStatusInterface,
                                  topMoviesCache: TopMoviesCacheInterface,
                                  popularMoviesCache: PopularMoviesCacheInterface,
                                  ioScheduler : Scheduler
        ) : MoviesProviderInterface = MoviesProvider(apiHolder, networkStatus, topMoviesCache,
                                                        popularMoviesCache, ioScheduler)
    }

}