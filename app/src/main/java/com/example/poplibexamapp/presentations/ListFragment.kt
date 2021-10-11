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
import com.example.poplibexamapp.presenters.ListRVAdapter
import io.reactivex.rxjava3.core.Scheduler
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class ListFragment: MvpDIFragment(R.layout.fragment_list), ListFragmentView {

    companion object Factory {
       private const val ARG_STRING = "arg_string"

        fun newInstance(testString: String): Fragment {
            val fragment = ListFragment()
            fragment.arguments = bundleOf(ARG_STRING to testString)
            return fragment
        }
    }

    @Inject
    lateinit var router: Router
    @Inject
    lateinit var mainThread: Scheduler
    @Inject
    lateinit var imageLoader: GlideImageLoader

    private val testString: String by lazy { arguments?.getString(ARG_STRING).orEmpty() }

    private val repository = NetSourceRepository(ApiHolder.api)

    private val presenter by moxyPresenter { ListPresenter (mainThread, router, repository) }

    private val viewBinding: FragmentListBinding by viewBinding()

    private var adapter: ListRVAdapter? = null

    override fun setTest(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show()
    }

    override fun initRVList() {
        viewBinding.rvList.layoutManager = LinearLayoutManager(context)
        adapter = ListRVAdapter(presenter.listItemsPresenter, imageLoader)
        viewBinding.rvList.adapter = adapter
    }

    override fun setList() {
        adapter?.notifyDataSetChanged()
    }
}