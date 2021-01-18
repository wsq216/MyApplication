package com.example.myapplication.model.zt

data class CategroyListData(
    val count: Int,
    val currentPage: Int,
    val `data`: List<DataX>,
    val filterCategory: List<FilterCategory>,
    val goodsList: List<Goods>,
    val pageSize: Int,
    val totalPages: Int
)

data class DataX(
    val id: Int,
    val list_pic_url: String,
    val name: String,
    val retail_price: String
)

data class FilterCategory(
    val checked: Boolean,
    val id: Int,
    val name: String
)

data class Goods(
    val id: Int,
    val list_pic_url: String,
    val name: String,
    val retail_price: Int
)