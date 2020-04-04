package com.ktea.order.injection.module


import com.ktea.order.service.OrderService
import com.ktea.order.service.impl.OrderServiceImpl
import dagger.Module
import dagger.Provides


@Module
class OrderModule {

    @Provides
    fun provider(orderService: OrderServiceImpl): OrderService {
        return orderService
    }
}