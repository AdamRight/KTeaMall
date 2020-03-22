package com.ktea.user.ui.activity

import android.os.Bundle
import android.view.View
import com.ktea.base.ext.enable
import com.ktea.base.ext.onClick
import com.ktea.base.ui.activity.BaseMvpActivity
import com.ktea.user.R
import com.ktea.user.data.protocol.UserInfo
import com.ktea.user.injection.component.DaggerUserComponent
import com.ktea.user.presenter.LoginPresenter
import com.ktea.user.presenter.view.LoginView
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Created by jiangtea on 2020/3/22.
 */
class LoginActivity: BaseMvpActivity<LoginPresenter>(),LoginView,View.OnClickListener {

    override fun injectComponent() {
        DaggerUserComponent.builder()
                .activityComponent(mActivityComponent)
                .build()
                .inject(this)
        mPresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()
    }

    private fun initView() {
        mLoginBtn.enable(mMobileEt) { isBtnEnable() }
        mLoginBtn.enable(mPwdEt) { isBtnEnable() }

        mLoginBtn.onClick(this)
        mForgetPwdTv.onClick(this)
        mHeaderBar.getRightView().onClick(this)
    }

    /**
     *  登录回调
     */
    override fun onLoginResult(result: UserInfo) {
        toast("登录成功")
        //UserPrefsUtils.putUserInfo(result)
        finish()
    }

    /**
     * 点击事件
     */
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.mRightTv ->
                startActivity<RegisterActivity>()
            R.id.mLoginBtn ->{
                //mPresenter.login(mMobileEt.text.toString(), mPwdEt.text.toString(), mPushProvider?.getPushId() ?: "")
            }
            R.id.mForgetPwdTv -> {
                //startActivity<ForgetPwdActivity>()
            }
        }
    }

    /**
     * 判断按钮是否可用
     */
    private fun isBtnEnable(): Boolean {
        return mMobileEt.text.isNullOrEmpty().not() &&
                mPwdEt.text.isNullOrEmpty().not()
    }
}