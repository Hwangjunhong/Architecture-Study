package com.hong.navermoviesearch.ext

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.hong.navermoviesearch.R

fun ImageView.loadImageView(imageUrl: String) {
    Glide.with(context)
        .load(imageUrl)
        .apply(RequestOptions.placeholderOf(R.drawable.ic_launcher_background).centerCrop())
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}