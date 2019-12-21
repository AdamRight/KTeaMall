package com.ktea.user.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Anko设置布局
 */
class AnkoLayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_test)

        verticalLayout {
            padding = 40
            editText {
                hint = "请输入内容"
                textSize = 20f
            }
            button{
                text = "收到的内容"
                onClick {
                    toast("${intent.getIntExtra("id", -1)}")
                }
            }
        }
    }

}
