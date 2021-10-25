package com.example.poplibexamapp.dagger

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.poplibexamapp.MoviesProvider
import com.example.poplibexamapp.MoviesProviderInterface
import com.example.poplibexamapp.SettingsProvider
import com.example.poplibexamapp.SettingsProviderInterface
import com.example.poplibexamapp.netSource.NetworkStatus
import com.example.poplibexamapp.database.*
import com.example.poplibexamapp.netSource.ApiHolder
import com.example.poplibexamapp.netSource.NetworkStatusInterface
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Singleton
import javax.inject.Inject

@Module
interface DataBaseModule {

    companion object {
        @Singleton
        @Provides
        fun provideDataBase(context: Context): LocalStorage =
            Room.databaseBuilder(context, LocalStorage::class.java, "movies.db")
                .build()

        @Reusable
        @Provides
        fun providePopularMoviesCache (dataBase: LocalStorage)
            : PopularMoviesCacheInterface = PopularMoviesCache(dataBase)

        @Reusable
        @Provides
        fun provideTopMoviesCache (dataBase: LocalStorage)
            : TopMoviesCacheInterface = TopMoviesCache(dataBase)

        @Reusable
        @Provides
        fun provideMoviesApi(): ApiHolder = ApiHolder()

        @Singleton
        @Provides
        fun provideSettingsProvider(sharedPreferences: SharedPreferences):
                SettingsProviderInterface = SettingsProvider(sharedPreferences)

        @Reusable
        @Provides
        fun provideMovieProvider (settingsProvider: SettingsProviderInterface, apiHolder: ApiHolder,
                                  networkStatus: NetworkStatusInterface,
                                  topMoviesCache: TopMoviesCacheInterface,
                                  popularMoviesCache: PopularMoviesCacheInterface,
                                  ioScheduler : Scheduler
        ) : MoviesProviderInterface = MoviesProvider(settingsProvider, apiHolder, networkStatus, topMoviesCache,
                                                        popularMoviesCache, ioScheduler)
    }

}