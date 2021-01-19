package com.example.myapplication.viewmodel

import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.home.HomeFragment
import com.example.myapplication.me.MeFragment
import com.example.myapplication.shop.ShopFragment
import com.example.myapplication.sort.SortFragment
import com.example.myapplication.topic.TopicFragment
import com.shop.base.BaseViewModel
import com.shop.net.Injection

class BindMainViewModel : BaseViewModel(Injection.repository) {

    var fragment: MutableList<Fragment> = mutableListOf()

    var listName = listOf<String>("首页", "专题", "分类", "购物车", "我的")

    var icon = listOf<Int>(
        R.drawable.selector_choice,
        R.drawable.selector_topic,
        R.drawable.selector_sort,
        R.drawable.selector_shoping,
        R.drawable.selector_me
    )

    init {
        fragment.add(HomeFragment())
        fragment.add(TopicFragment())
        fragment.add(SortFragment())
        fragment.add(ShopFragment.instance)
        fragment.add(MeFragment())
    }


}