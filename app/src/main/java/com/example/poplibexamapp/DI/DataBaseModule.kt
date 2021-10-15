package com.example.poplibexamapp.DI

import android.content.Context
import androidx.room.Room
import com.example.poplibexamapp.MainRepository
import com.example.poplibexamapp.MainRepositoryInterface
import com.example.poplibexamapp.NetworkStatus
import com.example.poplibexamapp.database.LocalStorage
import com.example.poplibexamapp.database.MoviesCache
import com.example.poplibexamapp.database.MoviesCacheInterface
import com.example.poplibexamapp.netSource.ApiHolder
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
        fun provideMoviesCache (dataBase: LocalStorage) : MoviesCacheInterface = MoviesCache(dataBase)

        @Singleton
        @Provides
        fun provideMoviesApi(): ApiHolder = ApiHolder()

        @Singleton
        @Provides
        fun provideMainRepository (apiHolder: ApiHolder,
                                   networkStatus: NetworkStatus,
                                   moviesCache: MoviesCacheInterface,
                                   ioScheduler : Scheduler
        ) : MainRepositoryInterface = MainRepository(apiHolder, networkStatus, moviesCache, ioScheduler)
    }

}