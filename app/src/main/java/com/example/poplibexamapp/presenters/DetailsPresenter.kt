package com.example.poplibexamapp.presenters

import com.example.poplibexamapp.MainRepository
import com.example.poplibexamapp.NetworkStatus
import com.example.poplibexamapp.data.MovieDataClass
import com.example.poplibexamapp.database.LocalStorage
import com.example.poplibexamapp.netSource.ApiHolder
import com.example.poplibexamapp.presentations.DetailsView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter

class DetailsPresenter(
    private val itemID: String,
    //private val customSchedulers: CustomSchedulersInterface,
    private val router: Router,
    private val dataBase: LocalStorage,
    private val networkStatus: NetworkStatus,
    //private val repository: MainRepositoryInterface
    ): MvpPresenter<DetailsView>() {

    private val disposable = CompositeDisposable()

    private val repository = MainRepository(ApiHolder.api, dataBase, networkStatus)

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        disposable.addAll(
            repository.getMovieByID(itemID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {onResult(it)},
                    {print(it)}
                )
        )
    }

    private fun onResult(movieData: MovieDataClass) {
        viewState.setDetails(movieData)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}