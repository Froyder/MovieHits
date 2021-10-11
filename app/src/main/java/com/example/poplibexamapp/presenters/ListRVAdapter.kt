package com.example.poplibexamapp.presenters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.poplibexamapp.IListViewPresenter
import com.example.poplibexamapp.ImageLoaderInterface
import com.example.poplibexamapp.ListItemView
import com.example.poplibexamapp.databinding.ItemListElementBinding

class ListRVAdapter(
    private val presenter: IListViewPresenter,
    val imageLoader: ImageLoaderInterface<ImageView>
    ) : RecyclerView.Adapter<ListRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemListElementBinding.inflate(LayoutInflater.from(parent.context), parent, false)).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = presenter.bindView(holder.apply { pos = position })

    inner class ViewHolder(val vb: ItemListElementBinding) : RecyclerView.ViewHolder(vb.root),
        ListItemView {
        override var pos = -1

        override fun setTitle(text: String) = with(vb) {
            tvTitle.text = text
        }

        override fun setPoster(url: String) {
            imageLoader.loadInto(url, vb.ivPoster)
        }
    }
}