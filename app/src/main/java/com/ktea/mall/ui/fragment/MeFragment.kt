package com.ktea.mall.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ktea.base.ext.loadUrl
import com.ktea.base.ext.onClick
import com.ktea.base.ui.fragment.BaseFragment
import com.ktea.base.utils.AppPrefsUtils
import com.ktea.mall.R
import com.ktea.mall.ui.activity.SettingActivity
import com.ktea.provider.common.ProviderConstant
import com.ktea.provider.common.afterLogin
import com.ktea.provider.common.isLogined
import com.ktea.user.ui.activity.UserInfoActivity
import kotlinx.android.synthetic.main.fragment_me.*
import org.jetbrains.anko.support.v4.startActivity

/**
 * Created by jiangtea on 2020/3/28.
 */
class MeFragment : BaseFragment(), View.OnClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_me, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    /**
     * 初始化视图
     */
    private fun initView() {
        mUserIconIv.onClick(this)
        mUserNameTv.onClick(this)

        mWaitPayOrderTv.onClick(this)
        mWaitConfirmOrderTv.onClick(this)
        mCompleteOrderTv.onClick(this)
        mAllOrderTv.onClick(this)
        mAddressTv.onClick(this)
        mShareTv.onClick(this)
        mSettingTv.onClick(this)
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    private fun loadData() {
        if (isLogined()) {
            val userIcon = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ICON)
            if (userIcon.isNotEmpty()) {
                mUserIconIv.loadUrl(userIcon)
            }
            mUserNameTv.text = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_NAME)
        } else {
            mUserIconIv.setImageResource(R.drawable.icon_default_user)
            mUserNameTv.text = getString(R.string.un_login_text)
        }
    }

    /**
     *点击事件
     */
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.mUserIconIv, R.id.mUserNameTv -> {
                afterLogin {
                    startActivity<UserInfoActivity>()
                }
            }
            /*R.id.mWaitPayOrderTv -> {
               startActivity<OrderActivity>(OrderConstant.KEY_ORDER_STATUS to OrderStatus.ORDER_WAIT_PAY)
           }
           R.id.mWaitConfirmOrderTv -> {
               startActivity<OrderActivity>(OrderConstant.KEY_ORDER_STATUS to OrderStatus.ORDER_WAIT_CONFIRM)
           }
           R.id.mCompleteOrderTv -> {
               startActivity<OrderActivity>(OrderConstant.KEY_ORDER_STATUS to OrderStatus.ORDER_COMPLETED)
           }
           R.id.mAllOrderTv -> {
               afterLogin {
                   startActivity<OrderActivity>()
               }
           }

           R.id.mAddressTv -> {
               afterLogin {
                   startActivity<ShipAddressActivity>()
               }
           }
           R.id.mShareTv -> {
               toast(R.string.coming_soon_tip)
           }*/
            R.id.mSettingTv -> {
                startActivity<SettingActivity>()
            }
        }
    }
}