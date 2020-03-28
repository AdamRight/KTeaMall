package com.ktea.mall.ui.activity

import android.os.Bundle
import com.ktea.base.ext.onClick
import com.ktea.base.ui.activity.BaseActivity
import com.ktea.mall.R
import com.ktea.user.utils.UserPrefsUtils
import kotlinx.android.synthetic.main.activity_setting.*
import org.jetbrains.anko.toast


class SettingActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        mUserProtocolTv.onClick {
            toast("用户协议")
        }
        mFeedBackTv.onClick {
            toast("反馈意见")
        }
        mAboutTv.onClick {
            toast("关于")
        }

        //退出登录，清空本地用户数据
        mLogoutBtn.onClick {
            UserPrefsUtils.putUserInfo(null)
            finish()
        }
    }
}