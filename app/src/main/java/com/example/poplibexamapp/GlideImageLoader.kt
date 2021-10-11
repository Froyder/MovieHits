package com.example.poplibexamapp

import android.widget.ImageView
import com.bumptech.glide.Glide

class GlideImageLoader : ImageLoaderInterface<ImageView> {
    override fun loadInto(url: String, container: ImageView) {
        Glide.with(container.context)
            .load(url)
            .into(container)
    }
}