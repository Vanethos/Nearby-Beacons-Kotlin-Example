package com.vanethos.nearbyservice.di.modules.services

import android.app.Service
import android.content.Context
import com.vanethos.nearbyservice.di.ServiceContext
import dagger.Module
import dagger.Provides
import dagger.Binds



@Module
abstract class BaseServiceModule<S : Service> {
    @Binds
    internal abstract fun service(service: S): Service

    @Module
    companion object {
        @JvmStatic
        @Provides
        @ServiceContext
        fun provideContext(service: Service): Context = service
    }
}