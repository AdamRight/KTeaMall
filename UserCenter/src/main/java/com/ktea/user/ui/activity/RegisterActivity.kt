package com.ktea.user.ui.activity

import android.os.Bundle
import android.view.View
import com.ktea.base.common.AppManager
import com.ktea.base.ext.enable
import com.ktea.base.ext.onClick
import com.ktea.base.ui.activity.BaseMvpActivity
import com.ktea.user.R
import com.ktea.user.injection.component.DaggerUserComponent
import com.ktea.user.injection.module.UserModule
import com.ktea.user.presenter.RegisterPresenter
import com.ktea.user.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView, View.OnClickListener {

    override fun injectComponent() {
        DaggerUserComponent.builder()
                .activityComponent(mActivityComponent)
                .userModule(UserModule())
                .build()
                .inject(this)
        mPresenter.mView = this
    }

    private var pressTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initView()
    }

    private fun initView() {
        //enable--手动实现扩展Button可用性
        mRegisterBtn.enable(mMobileEt, { isBtnEnable()})
        mRegisterBtn.enable(mVerifyCodeEt, { isBtnEnable() })
        mRegisterBtn.enable(mPwdEt, { isBtnEnable() })
        mRegisterBtn.enable(mPwdConfirmEt, { isBtnEnable() })

        mVerifyCodeBtn.onClick(this)
        mRegisterBtn.onClick(this)
        mHeaderBar.getRightView().onClick(this)
    }

    /**
     * 注册回调
     */
    override fun onRegisterResult(result: String) {
        toast(result)
    }

    /**
     * 判断按钮是否可用
     */
    private fun isBtnEnable(): Boolean {
        //.isNullOrEmpty().not()--为空再取反
        return mMobileEt.text.isNullOrEmpty().not() &&
                mVerifyCodeEt.text.isNullOrEmpty().not() &&
                mPwdEt.text.isNullOrEmpty().not() &&
                mPwdConfirmEt.text.isNullOrEmpty().not()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mVerifyCodeBtn -> {
                mVerifyCodeBtn.requestSendVerifyNumber()
                toast("发送验证成功")
            }
            R.id.mRegisterBtn -> {
                mPresenter.register(mMobileEt.text.toString(), mPwdEt.text.toString(), mVerifyCodeEt.text.toString())
            }
            R.id.mRightTv -> {
                startActivity<LoginActivity>()
            }
        }
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
