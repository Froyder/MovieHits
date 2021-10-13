package com.example.poplibexamapp.presenters

import com.example.poplibexamapp.BuildConfig
import com.example.poplibexamapp.CustomSchedulersInterface
import com.example.poplibexamapp.MainRepositoryInterface
import com.example.poplibexamapp.presentations.DetailsView
import com.github.terrakok.cicerone.Router
import io.reactivex.disposables.CompositeDisposable
import moxy.MvpPresenter

class DetailsPresenter(
    private val itemID: String,
    private val customSchedulers: CustomSchedulersInterface,
    private val router: Router,
    private val repository: MainRepositoryInterface): MvpPresenter<DetailsView>() {

    private val disposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        disposable.addAll(
            repository.getItemByID(itemID)
                .observeOn(customSchedulers.mainThread())
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