package com.ktea.user.data.repository

import com.ktea.base.data.net.RetrofitFactory
import com.ktea.base.data.protocol.BaseResp
import com.ktea.user.data.api.UploadApi
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by jiangtea on 2020/3/28.
 */
class UploadRepository @Inject constructor() {
    /**
     *获取七牛云上传凭证
     */
    fun getUploadToken(): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(UploadApi::class.java)
                .getUploadToken()
    }
}