package com.ktea.user.data.api

import com.ktea.base.data.protocol.BaseResp
import io.reactivex.Observable
import retrofit2.http.POST

/**
 * Created by jiangtea on 2020/3/28.
 */
interface UploadApi {
    /**
     *获取七牛云上传凭证
     */
    @POST("common/getUploadToken")
    fun getUploadToken(): Observable<BaseResp<String>>
}