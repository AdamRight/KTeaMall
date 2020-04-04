package com.ktea.paysdk.injection.module

import com.ktea.paysdk.service.PayService
import com.ktea.paysdk.service.impl.PayServiceImpl
import dagger.Module
import dagger.Provides

@Module
class PayModule {

    @Provides
    fun providerPayService(payService: PayServiceImpl): PayService {
        return payService
    }
}