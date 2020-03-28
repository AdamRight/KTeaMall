package com.ktea.user.injection.module

import com.ktea.user.service.UploadService
import com.ktea.user.service.impl.UploadServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by jiangtea on 2020/3/28.
 */
@Module
class UploadModule {

    @Provides
    fun providerUploadService(uploadService: UploadServiceImpl): UploadService {
        return uploadService
    }
}