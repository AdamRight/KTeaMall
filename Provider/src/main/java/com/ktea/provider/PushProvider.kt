package com.ktea.provider

import com.alibaba.android.arouter.facade.template.IProvider

interface PushProvider : IProvider {
    fun getPushId(): String
}