package com.ktea.base.injection.component


import android.content.Context
import com.ktea.base.injection.module.AppModule
import dagger.Component
import javax.inject.Singleton

/**
 *  Application级别Component
 */
@Singleton
@Component(modules = [(AppModule::class)])
interface AppComponent {
    fun context(): Context
}