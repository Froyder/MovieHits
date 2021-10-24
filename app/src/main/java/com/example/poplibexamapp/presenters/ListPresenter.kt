package com.example.poplibexamapp.presenters

import com.example.poplibexamapp.*
import com.example.poplibexamapp.model.MovieDataClass
import com.example.poplibexamapp.model.MoviesList
import com.example.poplibexamapp.presentations.DetailsScreen
import com.example.poplibexamapp.presentations.ListFragmentView
import com.github.terrakok.cicerone.Router
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter

class ListPresenter @AssistedInject constructor(
    private val settingsProvider: SettingsProviderInterface,
    private val router: Router,
    private val moviesProvider: MoviesProviderInterface,
): MvpPresenter<ListFragmentView>() {

    class ListItemsPresenter : IListViewPresenter {
        val mainList = mutableListOf<MovieDataClass>()
        override var itemClickListener: ((ListItemView) -> Unit)? = null

        override fun getCount() = mainList.size

        override fun bindView(view: ListItemView) {
            val listItem = mainList[view.pos]
            view.setMovieData(listItem)
            view.setPoster("http://image.tmdb.org/t/p/w500${listItem.poster_path}")
        }
    }

    val listItemsPresenter = ListItemsPresenter()
    private val disposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initRVList()
        loadData()

        listItemsPresenter.itemClickListener = { itemView ->
            val item = listItemsPresenter.mainList[itemView.pos]
            router.navigateTo(DetailsScreen(item.id.toString()))
        }
    }

    private fun loadData() {
        disposable.addAll(
            moviesProvider.getMoviesList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {onResult(it)},
                    {onError(it)}
                )
        )
    }

    private fun onResult(list: MoviesList) {
        listItemsPresenter.mainList.clear()
        listItemsPresenter.mainList.addAll(list.results)
        viewState.setList(settingsProvider.headerSettings())
    }

    private fun onError(throwable: Throwable){
        viewState.onError(throwable)
        println(throwable)
    }

    fun onTopButtonClicked(){
        settingsProvider.saveListSettings("TOP_RATED")
        loadData()
    }

    fun onPopButtonClicked(){
        settingsProvider.saveListSettings("POPULAR")
        loadData()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}