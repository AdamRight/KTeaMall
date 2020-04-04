package com.ktea.order.injection.module

import com.ktea.order.service.ShipAddressService
import com.ktea.order.service.impl.ShipAddressServiceImpl
import dagger.Module
import dagger.Provides

@Module
class ShipAddressModule {

    @Provides
    fun provideShipAddressService(shipAddressService: ShipAddressServiceImpl): ShipAddressService {
        return shipAddressService
    }
}