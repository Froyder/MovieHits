package com.example.poplibexamapp.presentations

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.poplibexamapp.presenters.ListPresenter
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.poplibexamapp.*
import com.example.poplibexamapp.databinding.FragmentListBinding
import com.example.poplibexamapp.presenters.ListRVAdapter
import moxy.ktx.moxyPresenter

class ListFragment: MvpDIFragment(R.layout.fragment_list), ListFragmentView {

    companion object Factory {
        fun newInstance(): Fragment = ListFragment()
    }

    private val presenter by moxyPresenter {
        ListPresenter (appContext, router, moviesProvider )
    }

    private val viewBinding: FragmentListBinding by viewBinding()

    private var adapter: ListRVAdapter? = null

    override fun initRVList() {
        viewBinding.rvList.layoutManager = LinearLayoutManager(appContext)
        adapter = ListRVAdapter(presenter.listItemsPresenter, imageLoader)
        viewBinding.rvList.adapter = adapter
    }

    override fun setList(headerText: String) {
        viewBinding.tvHeader.text = headerText
        adapter?.notifyDataSetChanged()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.topButton.setOnClickListener { presenter.onTopButtonClicked() }
        viewBinding.popButton.setOnClickListener { presenter.onPopButtonClicked() }
    }

    override fun backPressed() = presenter.backPressed()

    override fun onError(throwable: Throwable) {
        Toast.makeText(appContext, "Data loading error: $throwable", Toast.LENGTH_LONG).show()
    }
}