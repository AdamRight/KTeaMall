package com.ktea.user.service.impl

import com.ktea.base.ext.convert
import com.ktea.user.data.repository.UploadRepository
import com.ktea.user.service.UploadService
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by jiangtea on 2020/3/28.
 */
class UploadServiceImpl @Inject constructor() : UploadService {
    @Inject
    lateinit var reponsitory: UploadRepository

    override fun getUploadToken(): Observable<String> {
        return reponsitory.getUploadToken().convert()
    }
}