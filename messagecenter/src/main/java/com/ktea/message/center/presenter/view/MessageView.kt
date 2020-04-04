package com.ktea.message.center.presenter.view

import com.ktea.base.presenter.view.BaseView
import com.ktea.message.center.data.protocol.Message


/*
    消息列表 视图回调
 */
interface MessageView : BaseView {

    //获取消息列表回调
    fun onGetMessageResult(result:MutableList<Message>?)
}
