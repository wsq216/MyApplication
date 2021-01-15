package com.example.myapplication.data.newgoods


data class HotData(
    val bannerInfo: BannerInfo
)

data class BannerInfo(
    val img_url: String,
    val name: String,
    val url: String
)