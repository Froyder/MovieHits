package com.example.poplibexamapp.presenters

import android.content.Context
import android.content.SharedPreferences
import com.example.poplibexamapp.*
import com.example.poplibexamapp.model.MovieDataClass
import com.example.poplibexamapp.model.MoviesList
import com.example.poplibexamapp.presentations.DetailsScreen
import com.example.poplibexamapp.presentations.ListFragmentView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter

private const val POPULAR = "popular"
private const val TOP_RATED = "top_rated"
private const val HEADER_TOP = "Top rated movies:"
private const val HEADER_POP = "Now in theatres:"

class ListPresenter(
    private val sharedPreferences: SharedPreferences,
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

    private var listSettings = sharedPreferences.getString("LIST_TO_SHOW", POPULAR)
    private var headerText = if (listSettings == POPULAR) HEADER_POP else HEADER_TOP

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initRVList()
        listSettings?.let { loadData(it) }

        listItemsPresenter.itemClickListener = { itemView ->
            val item = listItemsPresenter.mainList[itemView.pos]
            router.navigateTo(DetailsScreen(item.id.toString()))
        }
    }

    private fun loadData(listToShow: String) {
        disposable.addAll(
            moviesProvider.getMoviesList(listToShow)
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
        viewState.setList(headerText)
    }

    private fun onError(throwable: Throwable){
        viewState.onError(throwable)
        println(throwable)
    }

    fun onTopButtonClicked(){
        sharedPreferences.edit().putString("LIST_TO_SHOW", TOP_RATED).apply()
        headerText = HEADER_TOP
        loadData(TOP_RATED)
    }

    fun onPopButtonClicked(){
        sharedPreferences.edit().putString("LIST_TO_SHOW", POPULAR).apply()
        headerText = HEADER_POP
        loadData(POPULAR)
    }

    fun backPressed(): Boolean {
        router.finishChain()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}