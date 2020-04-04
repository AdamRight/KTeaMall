package com.ktea.paysdk.ui.activity

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alipay.sdk.app.EnvUtils
import com.alipay.sdk.app.PayTask
import com.ktea.base.ext.onClick
import com.ktea.base.ui.activity.BaseMvpActivity
import com.ktea.base.utils.YuanFenConverter
import com.ktea.paysdk.R
import com.ktea.paysdk.injection.component.DaggerPayComponent
import com.ktea.paysdk.injection.module.PayModule
import com.ktea.paysdk.presenter.PayPresenter
import com.ktea.paysdk.presenter.view.PayView
import com.ktea.provider.common.ProviderConstant
import com.ktea.provider.router.RouterPath
import kotlinx.android.synthetic.main.activity_cash_register.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread


@Route(path = RouterPath.PaySDK.PATH_PAY)
class CashRegisterActivity : BaseMvpActivity<PayPresenter>(), PayView, View.OnClickListener {

    //订单号
    @Autowired(name = ProviderConstant.KEY_ORDER_ID)
    @JvmField
    var mOrderId: Int = 0
    //订单总价格
    @Autowired(name = ProviderConstant.KEY_ORDER_PRICE)
    @JvmField
    var mTotalPrice: Long = 0

    /*
        Dagger注册
     */
    override fun injectComponent() {
        DaggerPayComponent.builder()
                .activityComponent(mActivityComponent)
                .payModule(PayModule())
                .build()
                .inject(this)
        mPresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cash_register)

        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX)

        initView()
    }


    /*
        初始化视图
     */
    private fun initView() {
        mAlipayTypeTv.isSelected = true
        mAlipayTypeTv.onClick(this)
        mWeixinTypeTv.onClick(this)
        mBankCardTypeTv.onClick(this)
        mPayBtn.onClick(this)
        mTotalPriceTv.text = YuanFenConverter.changeF2YWithUnit(mTotalPrice)
    }

    /*
         获取支付签名回调
     */
    override fun onGetSignResult(result: String) {
        doAsync {
            val resultMap: Map<String, String> = PayTask(this@CashRegisterActivity).payV2(result, true)
            uiThread {
                if (resultMap["resultStatus"].equals("9000")) {
                    mPresenter.payOrder(mOrderId)
                } else {
                    toast("支付失败${resultMap["memo"]}")
                }
            }
        }
    }

    /*
        支付订单回调
     */
    override fun onPayOrderResult(result: Boolean) {
        toast("支付成功")
        finish()
    }

    /*
        点击事件
     */
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.mAlipayTypeTv -> {
                updatePayType(true, false, false)
            }
            R.id.mWeixinTypeTv -> {
                updatePayType(false, true, false)
            }
            R.id.mBankCardTypeTv -> {
                updatePayType(false, false, true)
            }
            R.id.mPayBtn -> {
                mPresenter.getPaySign(mOrderId, mTotalPrice)
            }
        }
    }

    /*
       选择支付类型，UI变化
    */
    private fun updatePayType(isAliPay: Boolean, isWeixinPay: Boolean, isBankCardPay: Boolean) {
        mAlipayTypeTv.isSelected = isAliPay
        mWeixinTypeTv.isSelected = isWeixinPay
        mBankCardTypeTv.isSelected = isBankCardPay
    }
}