package com.example.poplibexamapp.presentations

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.poplibexamapp.databinding.FragmentListBinding
import com.example.poplibexamapp.presenters.ListPresenter
import com.github.terrakok.cicerone.Router
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.poplibexamapp.*
import com.example.poplibexamapp.database.LocalStorage
import com.example.poplibexamapp.database.MoviesCacheInterface
import com.example.poplibexamapp.presenters.ListRVAdapter
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class ListFragment: MvpDIFragment(R.layout.fragment_list), ListFragmentView {

    companion object Factory {
        fun newInstance(): Fragment = ListFragment()
    }

//    @Inject
//    lateinit var mainRepository: MainRepository

    private val presenter by moxyPresenter {
        ListPresenter (ioScheduler, router, dataBase, networkStatus, moviesCache )
    }

    private val viewBinding: FragmentListBinding by viewBinding()

    private var adapter: ListRVAdapter? = null

    override fun initRVList() {
        viewBinding.rvList.layoutManager = LinearLayoutManager(context)
        adapter = ListRVAdapter(presenter.listItemsPresenter, imageLoader)
        viewBinding.rvList.adapter = adapter
    }

    override fun setList() {
        adapter?.notifyDataSetChanged()
    }

    override fun onError(throwable: Throwable) {
        Toast.makeText(context, "Data loading error: $throwable", Toast.LENGTH_LONG).show()
    }

    override fun onBackClicked() {
        presenter.backButtonClicked()
    }
}