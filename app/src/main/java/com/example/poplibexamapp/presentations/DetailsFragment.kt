package com.example.poplibexamapp.presentations

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.poplibexamapp.databinding.FragmentDetailsBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.poplibexamapp.*
import com.example.poplibexamapp.data.MovieDataClass
import com.example.poplibexamapp.database.LocalStorage
import com.example.poplibexamapp.presenters.DetailsPresenter
import com.github.terrakok.cicerone.Router
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
    @Inject
    lateinit var dataBase: LocalStorage
//    @Inject
//    lateinit var mainRepository: MainRepository

    private val itemID: String by lazy { arguments?.getString(ARG_STRING).orEmpty() }

    //private val repository = MainRepository(ApiHolder.api, dataBase, networkStatus)

    private val presenter by moxyPresenter { DetailsPresenter (itemID, router, dataBase, networkStatus) }

    private val viewBinding: FragmentDetailsBinding by viewBinding()

    override fun setDetails(movieData : MovieDataClass) {
        imageLoader.loadInto("http://image.tmdb.org/t/p/w500${movieData.poster_path}", viewBinding.ivPoster)
        viewBinding.itemTitle.text = movieData.title
        viewBinding.itemDetails.text = movieData.overview
    }

}