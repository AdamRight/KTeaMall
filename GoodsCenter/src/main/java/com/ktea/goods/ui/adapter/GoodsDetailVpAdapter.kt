package com.ktea.goods.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.ktea.goods.ui.fragment.GoodsDetailTabOneFragment
import com.ktea.goods.ui.fragment.GoodsDetailTabTwoFragment

class GoodsDetailVpAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val titles = arrayOf("商品", "详情")

    override fun getItem(position: Int): Fragment {
        return if (position == 0) {
            GoodsDetailTabOneFragment()
        } else {
            GoodsDetailTabTwoFragment()
        }
    }

    override fun getCount(): Int {
        return titles.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }
}