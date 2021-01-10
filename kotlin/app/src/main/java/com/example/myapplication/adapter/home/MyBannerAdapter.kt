package com.example.myapplication.adapter.home

import android.content.Context
import android.widget.ImageView
import androidx.core.view.get
import com.bumptech.glide.Glide
import com.example.myapplication.data.Banner

import com.youth.banner.loader.ImageLoader

class MyBannerAdapter: ImageLoader() {
    override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
        var item = path as Banner
        Glide.with(context!!).load(item.image_url).into(imageView!!)
    }
}