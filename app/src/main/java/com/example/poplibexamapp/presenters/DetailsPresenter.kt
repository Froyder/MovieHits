package com.example.poplibexamapp.presenters

import com.example.poplibexamapp.MoviesProviderInterface
import com.example.poplibexamapp.model.MovieDataClass
import com.example.poplibexamapp.presentations.DetailsView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter

class DetailsPresenter(
    private val itemID: String,
    private val router: Router,
    private val repository: MoviesProviderInterface
    ): MvpPresenter<DetailsView>() {

    private val disposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        disposable.addAll(
            repository.getMovieByID(itemID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {onResult(it)},
                    {onError(it)}
                )
        )
    }

    private fun onResult(movieData: MovieDataClass) {
        viewState.setDetails(movieData)
    }

    private fun onError(throwable: Throwable){
        viewState.onError(throwable)
        println(throwable)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}