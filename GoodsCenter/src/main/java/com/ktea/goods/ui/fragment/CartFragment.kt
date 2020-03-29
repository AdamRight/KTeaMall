package com.ktea.goods.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ktea.base.ui.fragment.BaseFragment
import com.ktea.goods.R

class CartFragment : BaseFragment() {

    //private lateinit var mAdapter: CartGoodsAdapter

    //private var mTotalPrice: Long = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

   /* override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserve()
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }

    *//*
        初始化视图和事件
     *//*
    private fun initView() {
        mCartGoodsRv.layoutManager = LinearLayoutManager(context)
        mAdapter = CartGoodsAdapter(context!!)
        mCartGoodsRv.adapter = mAdapter

        mHeaderBar.getRightView().onClick {
            refreshEditStatus()
        }

        //全选按钮事件
        mAllCheckedCb.onClick {
            for (item in mAdapter.dataList) {
                item.isSelected = mAllCheckedCb.isChecked
            }
            mAdapter.notifyDataSetChanged()
            updateTotalPrice()
        }
        //删除按钮事件
        mDeleteBtn.onClick {
            val cartIdList: MutableList<Int> = arrayListOf()
            mAdapter.dataList.filter { it.isSelected }
                    .mapTo(cartIdList) {
                        it.id
                    }
            if (cartIdList.size == 0) {
                toast("请选择需要删除的数据")
            } else {
                mPresenter.deleteCartList(cartIdList)
            }
        }

        //结算按钮事件
        mSettleAccountsBtn.onClick {
            val cartGoodsList: MutableList<CartGoods> = arrayListOf()
            mAdapter.dataList.filter { it.isSelected }
                    .mapTo(cartGoodsList) {
                        it
                    }
            if (cartGoodsList.size == 0) {
                toast("请选择需要提交的数据")
            } else {
                mPresenter.submitCart(cartGoodsList, mTotalPrice)
            }
        }
    }

    *//*
        刷新是否为编辑状态
     *//*
    private fun refreshEditStatus() {
        val isEditStatus = getString(R.string.common_edit) == mHeaderBar.getRightText()
        mTotalPriceTv.setVisible(isEditStatus.not())
        mSettleAccountsBtn.setVisible(isEditStatus.not())
        mDeleteBtn.setVisible(isEditStatus)
        mHeaderBar.getRightView().text = if (isEditStatus) {
            getString(R.string.common_complete)
        } else {
            getString(R.string.common_edit)
        }
    }

    *//*
        加载数据
     *//*
    private fun loadData() {
        mMultiStateView.startLoading()
        mPresenter.getCartList()
    }

    private fun initObserve() {
        Bus.observe<CartAllCheckedEvent>()
                .subscribe {
                    mAllCheckedCb.isChecked = it.isAllChecked
                    updateTotalPrice()
                }.registerInBus(this)

        Bus.observe<UpdateTotalPriceEvent>()
                .subscribe {
                    updateTotalPrice()
                }.registerInBus(this)
    }

    *//*
        更新总价
     *//*
    @SuppressLint("SetTextI18n")
    private fun updateTotalPrice() {
        mTotalPrice = mAdapter.dataList
                .filter { it.isSelected }
                .map { it.goodsCount * it.goodsPrice }
                .sum()
        mTotalPriceTv.text = "合计：${YuanFenConverter.changeF2YWithUnit(mTotalPrice)}"
    }

    *//*
        Dagger注册
     *//*
    override fun injectComponent() {
        DaggerCartComponent.builder()
                .activityComponent(mActivityComponent)
                .cartModule(CartModule())
                .build()
                .inject(this)
        mPresenter.mView = this
    }

    *//*
        获取购物车列表 回调
     *//*
    override fun onGetCartListResult(result: MutableList<CartGoods>?) {
        if (result != null && result.size > 0) {
            mAdapter.setData(result)
            mHeaderBar.getRightView().setVisible(true)
            mAllCheckedCb.isChecked = false
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mHeaderBar.getRightView().setVisible(false)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
        //本地存储并发送事件刷新UI
        AppPrefsUtils.putInt(GoodsConstant.SP_CART_SIZE, result?.size ?: 0)
        Bus.send(UpdateCartSizeEvent())
        //更新总价
        updateTotalPrice()
    }

    *//*
        删除购物车 回调
     *//*
    override fun onDeleteCartListResult(result: Boolean) {
        toast("购物车删除成功")
        refreshEditStatus()
        loadData()
    }

    *//*
        提交购物车 回调
     *//*
    override fun onSubmitCartListResult(result: Int) {
        ARouter.getInstance().build(RouterPath.OrderCenter.PATH_ORDER_CONFIRM)
                .withInt(ProviderConstant.KEY_ORDER_ID, result)
                .navigation()
    }

    *//*
        设置Back是否可见
     *//*
    fun setBackVisible(isVisible: Boolean) {
        mHeaderBar.getLeftView().setVisible(isVisible)
    }*/
}