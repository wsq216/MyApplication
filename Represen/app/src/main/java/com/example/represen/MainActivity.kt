package com.example.represen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        var list = ArrayList<Fragment>()

        list.add(HomeFragment())
        list.add(HomeFragment())
        list.add(HomeFragment())
        list.add(HomeFragment())
        list.add(HomeFragment())

        viewpager!!.adapter = Viewpage(supportFragmentManager,list)

        var listName = arrayOf("首页","采购单","订单","会员","我的")

        var icon = arrayOf(R.drawable.selector_choice,
            R.drawable.selector_procurement,
            R.drawable.selector_indent,
            R.drawable.selector_member,
            R.drawable.selector_me)

        tabMode.setupWithViewPager(viewpager)
        for (i in list.indices){
            tabMode.getTabAt(i)?.setText(listName[i])?.setIcon(icon[i])
        }
    }

    class Viewpage(supportFragmentManager: FragmentManager, var list: ArrayList<Fragment>)
    :FragmentStatePagerAdapter(supportFragmentManager) {
        override fun getCount(): Int {
            return list.size
        }

        override fun getItem(position: Int): Fragment {
            return list.get(position)
        }

    }


}
