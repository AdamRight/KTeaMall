package com.ktea.base.ui.activity

import android.os.Bundle
import com.ktea.base.common.AppManager
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

/**
 * Created by jiangtea on 2019/12/21.
 */
open class BaseActivity : RxAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppManager.instance.addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        AppManager.instance.finishActivity(this)
    }
}