package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.MovementMethod
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.myapplication.home.HomeFragment
import com.example.myapplication.me.MeFragment
import com.example.myapplication.shop.ShopFragment
import com.example.myapplication.sort.SortFragment
import com.example.myapplication.topic.TopicFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity<T> : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        var list = ArrayList<Fragment>()
        list.add(HomeFragment())
        list.add(TopicFragment())
        list.add(SortFragment())
        list.add(ShopFragment())
        list.add(MeFragment())

        var listName = arrayOf("首页","专题","分类","购物车","我的")
        var icon = arrayOf(R.drawable.selector_choice,
            R.drawable.selector_topic,
            R.drawable.selector_sort,
            R.drawable.selector_shoping,
            R.drawable.selector_me)
        viewPager!!.adapter=ViewPage(supportFragmentManager,list)

        tabMode.setupWithViewPager(viewPager)
        for (i in list.indices){
            tabMode.getTabAt(i)?.setText(listName[i])?.setIcon(icon[i])
        }

    }

    class  ViewPage(fragmentManager: FragmentManager,val list: ArrayList<Fragment>)
        :FragmentStatePagerAdapter(fragmentManager){

        override fun getCount(): Int {
            return list.size
        }

        override fun getItem(position: Int): Fragment {
            return list.get(position)
        }

    }

}