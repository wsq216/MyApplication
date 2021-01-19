package com.example.myapplication.adapter.car


data class RelatedBase(
    val goodsList: List<Goods>
)

data class Goods(
    val id: Int,
    val list_pic_url: String,
    val name: String,
    val retail_price: String
)