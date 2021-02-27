package com.roshastudio.newsapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

object ImageLoder {
    @BindingAdapter("app:imgLoderNet")
    @JvmStatic
    fun imgLoderNet(imgeView: ImageView, url: Int) {
        Glide.with(imgeView).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).dontAnimate()
            .fitCenter().into(imgeView)
    }


}