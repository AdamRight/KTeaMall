package com.ktea.base.data.protocol

/**
 * Created by jiangtea on 2019/12/22.
 */
data class BaseResp<T>(val status: Int, val message: String, val data: T)