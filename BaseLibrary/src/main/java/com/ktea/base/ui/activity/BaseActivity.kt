package com.ktea.base.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import com.ktea.base.common.AppManager
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import org.jetbrains.anko.find

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

    //获取Window中视图content
    val contentView: View
        get() {
            val content = find<FrameLayout>(android.R.id.content)
            return content.getChildAt(0)
        }
}