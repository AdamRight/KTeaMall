package com.ktea.mall.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ktea.base.ext.loadUrl
import com.ktea.mall.R
import kotlinx.android.synthetic.main.layout_topic_item.view.*

/**
 * Created by jiangtea on 2020/3/28.
 */
class TopicAdapter (val context: Context, val list: List<String>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val rootView = LayoutInflater.from(this.context).inflate(R.layout.layout_topic_item, null)
        rootView.mTopicIv.loadUrl(list[position])
        container.addView(rootView)
        return rootView
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return this.list.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
        container.removeView(`object` as View)
    }
}