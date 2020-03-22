package com.ktea.user.ui.activity

import android.os.Bundle
import com.ktea.base.common.AppManager
import com.ktea.base.ext.onClick
import com.ktea.base.ui.activity.BaseMvpActivity
import com.ktea.user.R
import com.ktea.user.injection.component.DaggerUserComponent
import com.ktea.user.injection.module.UserModule
import com.ktea.user.presenter.RegisterPresenter
import com.ktea.user.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast


class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView {

    override fun injectComponent() {
        DaggerUserComponent.builder()
                .activityComponent(mActivityComponent)
                .userModule(UserModule())
                .build()
                .inject(this)
    }

    private var pressTime: Long = 0

    override fun onRegisterResult(result: String) {
        toast(result)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mvpDemo()

    }

    /**
     * mvp的使用
     */
    private fun mvpDemo() {
        //mPresenter = RegisterPresenter()
        mPresenter.mView = this

        btnRegister3.setOnClickListener {
            mPresenter.register("", "", "")
        }

        registerVerify.onClick {
            registerVerify.requestSendVerifyNumber()
        }
        /*registerVerify.setOnVerifyBtnClick(object :VerifyButton.OnVerifyBtnClick{
            override fun onClick() {
                toast("获取验证码")

            }
        })*/

    }


    /**
     * 返回键退出程序
     */
    override fun onBackPressed() {
        val time = System.currentTimeMillis()
        if (time - pressTime > 2000) {
            toast("再按一次退出程序")
            pressTime = time
        } else {
            AppManager.instance.exitApp(this)
        }

    }

}
