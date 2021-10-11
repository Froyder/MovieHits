package com.example.poplibexamapp.presentations

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.poplibexamapp.R
import com.example.poplibexamapp.databinding.FragmentDetailsBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.poplibexamapp.ApiHolder
import com.example.poplibexamapp.GlideImageLoader
import com.example.poplibexamapp.NetSourceRepository
import com.example.poplibexamapp.data.ItemDataClass
import com.example.poplibexamapp.presenters.DetailsPresenter
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class DetailsFragment: MvpDIFragment(R.layout.fragment_details), DetailsView {

    companion object Factory {
        private const val ARG_STRING = "arg_string"

        fun newInstance(itemID: String): Fragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundleOf(ARG_STRING to itemID)
            return fragment
        }
    }

    @Inject
    lateinit var router: Router
    @Inject
    lateinit var mainThread: Scheduler
    @Inject
    lateinit var imageLoader: GlideImageLoader

    private val itemID: String by lazy { arguments?.getString(ARG_STRING).orEmpty() }

    private val repository = NetSourceRepository(ApiHolder.api)

    private val presenter by moxyPresenter { DetailsPresenter (itemID, mainThread, router, repository) }

    private val viewBinding: FragmentDetailsBinding by viewBinding()

    override fun setDetails(itemData : ItemDataClass) {
        imageLoader.loadInto("http://image.tmdb.org/t/p/w500${itemData.poster_path}", viewBinding.ivPoster)
        viewBinding.itemId.text = itemData.id.toString()
        viewBinding.itemTitle.text = itemData.title
        viewBinding.itemDetails.text = itemData.overview

       // Toast.makeText(context, itemID, Toast.LENGTH_SHORT).show()
    }

}