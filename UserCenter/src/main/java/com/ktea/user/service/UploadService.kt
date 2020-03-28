package com.ktea.user.service

import io.reactivex.Observable

/**
 * Created by jiangtea on 2020/3/28.
 */
interface UploadService {
    fun getUploadToken(): Observable<String>
}