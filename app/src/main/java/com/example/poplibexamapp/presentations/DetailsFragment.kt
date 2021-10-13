package com.example.poplibexamapp.presentations

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.poplibexamapp.databinding.FragmentDetailsBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.poplibexamapp.*
import com.example.poplibexamapp.data.ItemDataClass
import com.example.poplibexamapp.netSource.ApiHolder
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
    lateinit var customSchedulers: CustomSchedulersInterface
    @Inject
    lateinit var imageLoader: GlideImageLoader
    @Inject
    lateinit var networkStatus: NetworkStatus

    private val itemID: String by lazy { arguments?.getString(ARG_STRING).orEmpty() }

    private val repository = NetSourceRepository(ApiHolder.api, customSchedulers)

    private val presenter by moxyPresenter { DetailsPresenter (itemID, customSchedulers, router, repository) }

    private val viewBinding: FragmentDetailsBinding by viewBinding()

    override fun setDetails(itemData : ItemDataClass) {
        imageLoader.loadInto("http://image.tmdb.org/t/p/w500${itemData.poster_path}", viewBinding.ivPoster)
        viewBinding.itemTitle.text = itemData.title
        viewBinding.itemDetails.text = itemData.overview

       // Toast.makeText(context, itemID, Toast.LENGTH_SHORT).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(context, "Details: " + networkStatus.netStatusSubject.value.toString(), Toast.LENGTH_LONG).show()
    }

}