package com.ktea.goods.ui.activity

import android.os.Bundle
import com.ktea.base.ui.activity.BaseActivity
import com.ktea.goods.R
import com.ktea.goods.ui.fragment.CartFragment


class CartActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_cart)
        (fragment as CartFragment).setBackVisible(true)
    }
}