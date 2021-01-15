package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.MovementMethod
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.home.HomeFragment
import com.example.myapplication.me.MeFragment
import com.example.myapplication.shop.ShopFragment
import com.example.myapplication.sort.SortFragment
import com.example.myapplication.topic.TopicFragment
import com.example.myapplication.viewmodel.BindMainViewModel
import com.shop.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : BaseActivity<BindMainViewModel, ActivityMainBinding>(
    R.layout.activity_main,
    BindMainViewModel::class.java
) {


    override fun initView() {

        var list: List<Fragment> = mViewModel.fragment

        viewPager!!.adapter = ViewPage(supportFragmentManager, list)

        tabMode.setupWithViewPager(viewPager)
        for (i in list.indices) {
            tabMode.getTabAt(i)?.setText(mViewModel.listName[i])?.setIcon(mViewModel.icon[i])
        }

    }

    class ViewPage(fragmentManager: FragmentManager, val list: List<Fragment>) :
        FragmentStatePagerAdapter(fragmentManager) {

        override fun getCount(): Int {
            return list.size
        }

        override fun getItem(position: Int): Fragment {
            return list.get(position)
        }

    }

    override fun initVM() {

    }

    override fun initData() {
    }

    override fun initVariable() {
    }


}