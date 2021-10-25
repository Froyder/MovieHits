package com.example.poplibexamapp.presentations

import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.poplibexamapp.*
import com.example.poplibexamapp.model.MovieDataClass
import com.example.poplibexamapp.databinding.FragmentDetailsBinding
import com.example.poplibexamapp.presenters.DetailsPresentersFactory
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
    lateinit var presenterFactory: DetailsPresentersFactory

    private val itemID: String by lazy { arguments?.getString(ARG_STRING).orEmpty() }

    private val presenter by moxyPresenter { presenterFactory.createDetailsPresenter(itemID) }

    private val viewBinding: FragmentDetailsBinding by viewBinding()

    override fun setDetails(movieData : MovieDataClass) {
        imageLoader.loadInto("http://image.tmdb.org/t/p/w500${movieData.poster_path}", viewBinding.ivPoster)
        viewBinding.itemTitle.text = movieData.title
        viewBinding.itemDetails.text = movieData.overview
        viewBinding.itemReleaseDate.text = "Release date: " +  movieData.release_date
        viewBinding.itemRating.text = "Rating: " + movieData.vote_average.toString()
    }

    override fun onError(throwable: Throwable) {
        Toast.makeText(context, "Data loading error: $throwable", Toast.LENGTH_LONG).show()
    }

}