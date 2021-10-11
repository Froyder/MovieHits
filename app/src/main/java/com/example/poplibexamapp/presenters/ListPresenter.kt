package com.example.poplibexamapp.presenters

import com.example.poplibexamapp.*
import com.example.poplibexamapp.data.ItemDataClass
import com.example.poplibexamapp.presentations.DetailsScreen
import com.example.poplibexamapp.presentations.ListFragmentView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter

class ListPresenter(
    private val mainThread: Scheduler,
    private val router: Router,
    private val repository: MainRepositoryInterface
): MvpPresenter<ListFragmentView>() {

    class ListItemsPresenter : IListViewPresenter {
        val mainList = mutableListOf<ItemDataClass>()
        override var itemClickListener: ((ListItemView) -> Unit)? = null

        override fun getCount() = mainList.size

        override fun bindView(view: ListItemView) {
            val listItem = mainList[view.pos]
            view.setTitle(listItem.title)
            view.setPoster("http://image.tmdb.org/t/p/w500${listItem.poster_path}")
        }
    }

    val listItemsPresenter = ListItemsPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initRVList()
        loadData()

        listItemsPresenter.itemClickListener = { itemView ->
            val item = listItemsPresenter.mainList[itemView.pos]
            router.navigateTo(DetailsScreen(item.id.toString()))
        }
    }

    private val disposableList = CompositeDisposable()

    private fun loadData() {
        disposableList.add(
            repository.getItemsList()
                .observeOn(mainThread)
                .subscribe({ onResult ->
                    listItemsPresenter.mainList.clear()
                    listItemsPresenter.mainList.addAll(onResult.results)
                    viewState.setList()
            },{
                println("ERRRRRRRRRRROOOOOOOOOORRRRRRRR $it")
            })
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        disposableList.dispose()
    }
}