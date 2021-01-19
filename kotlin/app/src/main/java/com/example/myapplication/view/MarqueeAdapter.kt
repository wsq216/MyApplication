package com.shop.ui.main.home.adapter

import android.content.Context
import android.widget.TextView
import com.example.myapplication.R
import com.xj.marqueeview.base.CommonAdapter
import com.xj.marqueeview.base.ViewHolder

class MarqueeAdapter(
    context: Context?,
    data: List<String?>?
) :
    CommonAdapter<String?>(context, R.layout.simple_text, data) {
    override fun convert(
        viewHolder: ViewHolder,
        item: String?,
        position: Int
    ) {
        val view: TextView = viewHolder.getView(R.id.tv_simple_text)
        view.text = item
    }

}
