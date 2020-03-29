package com.ktea.goods.injection.module

import com.ktea.goods.service.CartService
import com.ktea.goods.service.impl.CartServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by jiangtea on 2020/3/29.
 */
@Module
class CartModule {
    @Provides
    fun providerAddCart(cartService: CartServiceImpl): CartService {
        return cartService
    }
}