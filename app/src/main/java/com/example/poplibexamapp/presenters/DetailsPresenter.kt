package com.example.poplibexamapp.presenters

import com.example.poplibexamapp.BuildConfig
import com.example.poplibexamapp.MainRepositoryInterface
import com.example.poplibexamapp.presentations.DetailsView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter

class DetailsPresenter(
    private val itemID: String,
    private val mainThread: Scheduler,
    private val router: Router,
    private val repository: MainRepositoryInterface): MvpPresenter<DetailsView>() {

    private val disposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        disposable.addAll(
            repository.getItemByID(itemID)
                .observeOn(mainThread)
                .subscribe({item ->
                    viewState.setDetails(item)
                }, {
                    print(it)
                })
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}