package com.ktea.message.center.data.repository


import com.ktea.base.data.net.RetrofitFactory
import com.ktea.base.data.protocol.BaseResp
import com.ktea.message.center.data.api.MessageApi
import com.ktea.message.center.data.protocol.Message
import io.reactivex.Observable
import javax.inject.Inject


/*
   消息数据层
 */
class MessageRepository @Inject constructor() {

    /*
        获取消息列表
     */
    fun getMessageList(): Observable<BaseResp<MutableList<Message>?>> {
        return RetrofitFactory.instance.create(MessageApi::class.java).getMessageList()
    }

}
