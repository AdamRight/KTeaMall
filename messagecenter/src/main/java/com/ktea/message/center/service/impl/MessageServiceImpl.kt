package com.ktea.message.center.service.impl


import com.ktea.base.ext.convert
import com.ktea.message.center.data.protocol.Message
import com.ktea.message.center.data.repository.MessageRepository
import com.ktea.message.center.service.MessageService
import io.reactivex.Observable
import javax.inject.Inject

/*
   消息业务层
 */
class MessageServiceImpl @Inject constructor() : MessageService {

    @Inject
    lateinit var repository: MessageRepository

    /*
        获取消息列表
     */
    override fun getMessageList(): Observable<MutableList<Message>?> {
        return repository.getMessageList().convert()
    }

}
