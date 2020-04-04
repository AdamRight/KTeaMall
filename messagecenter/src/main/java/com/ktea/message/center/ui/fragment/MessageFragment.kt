package com.ktea.message.center.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eightbitlab.rxbus.Bus
import com.kennyc.view.MultiStateView
import com.ktea.base.ext.startLoading
import com.ktea.base.ui.fragment.BaseMvpFragment
import com.ktea.message.center.R
import com.ktea.message.center.data.protocol.Message
import com.ktea.message.center.injection.component.DaggerMessageComponent
import com.ktea.message.center.injection.module.MessageModule
import com.ktea.message.center.presenter.MessagePresenter
import com.ktea.message.center.presenter.view.MessageView
import com.ktea.message.center.ui.adapter.MessageAdapter
import com.ktea.provider.common.event.MessageBadgeEvent
import kotlinx.android.synthetic.main.fragment_message.*


class MessageFragment : BaseMvpFragment<MessagePresenter>(), MessageView {

    private lateinit var mAdapter: MessageAdapter
    /*
        Dagger注册
     */
    override fun injectComponent() {
        DaggerMessageComponent.builder()
                .activityComponent(mActivityComponent)
                .messageModule(MessageModule())
                .build()
                .inject(this)
        mPresenter.mView = this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_message, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    /*
        初始化视图
     */
    private fun initView() {
        mMessageRv.layoutManager = LinearLayoutManager(context)
        mAdapter = MessageAdapter(context!!)
        mMessageRv.adapter = mAdapter
    }

    /*
       加载数据
    */
    private fun loadData() {
        mMultiStateView.startLoading()
        mPresenter.getMessageList()
    }

    /*
        获取消息后回调
     */
    override fun onGetMessageResult(result: MutableList<Message>?) {
        if (result != null && result.size > 0) {
            mAdapter.setData(result)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            //数据为空
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    /*
        监听Fragment隐藏或显示
     */
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            Bus.send(MessageBadgeEvent(false))
        }
    }
}