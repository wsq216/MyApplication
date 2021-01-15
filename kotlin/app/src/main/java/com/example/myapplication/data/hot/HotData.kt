package com.example.myapplication.data.hot

data class HotData(
    val `data`: Data,
    val status: Status
)

data class Data(
    val comTotal: Int,
    val list: List<Discover>,
    val total: Int
)

data class Status(
    val message: String,
    val serverTime: String,
    val statusCode: Int
)

data class Discover(
    val corner : Int = 8,
    val author: String,
    val cagetory: String,
    val commID: Int,
    val content: String,
    val cover: String,
    val createTime: String,
    val detailUrl: String,
    val filePathList: List<FilePath>,
    val id: Int,
    val keyword: String,
    val listImages: String,
    val qiYuId: String,
    val reptileArticleID: Int,
    val sourceStr: String,
    val status: Int,
    val title: String,
    val type: Int
)

data class FilePath(
    val filePath: String
)