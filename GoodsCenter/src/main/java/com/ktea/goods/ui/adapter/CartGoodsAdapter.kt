package com.ktea.goods.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eightbitlab.rxbus.Bus
import com.ktea.base.ext.loadUrl
import com.ktea.base.ext.onClick
import com.ktea.base.ui.adapter.BaseRecyclerViewAdapter
import com.ktea.base.utils.YuanFenConverter
import com.ktea.base.widgets.DefaultTextWatcher
import com.ktea.goods.R
import com.ktea.goods.data.protocol.CartGoods
import com.ktea.goods.event.CartAllCheckedEvent
import com.ktea.goods.event.UpdateTotalPriceEvent
import com.ktea.goods.getEditText
import kotlinx.android.synthetic.main.layout_cart_goods_item.view.*


class CartGoodsAdapter(context: Context) : BaseRecyclerViewAdapter<CartGoods,
        CartGoodsAdapter.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_cart_goods_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]
        //是否选中
        holder.itemView.mCheckedCb.isChecked = model.isSelected
        //加载商品图片
        holder.itemView.mGoodsIconIv.loadUrl(model.goodsIcon)
        //商品描述
        holder.itemView.mGoodsDescTv.text = model.goodsDesc
        //商品SKU
        holder.itemView.mGoodsSkuTv.text = model.goodsSku
        //商品价格
        holder.itemView.mGoodsPriceTv.text = YuanFenConverter.changeF2YWithUnit(model.goodsPrice)
        //商品数量
        holder.itemView.mGoodsCountBtn.setCurrentNumber(model.goodsCount)

        //选中按钮事件
        holder.itemView.mCheckedCb.onClick {
            model.isSelected = holder.itemView.mCheckedCb.isChecked
            val isAllChecked = dataList.all {
                it.isSelected
            }
            Bus.send(CartAllCheckedEvent(isAllChecked))
        }
        holder.itemView.mGoodsCountBtn.getEditText().addTextChangedListener(object : DefaultTextWatcher() {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                super.onTextChanged(s, start, before, count)
                model.goodsCount = s.toString().toInt()
                Bus.send(UpdateTotalPriceEvent())
            }
        })

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}