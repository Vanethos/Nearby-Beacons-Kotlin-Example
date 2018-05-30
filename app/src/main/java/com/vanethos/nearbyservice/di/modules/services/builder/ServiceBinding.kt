package com.vanethos.nearbyservice.di.modules.services.builder

import com.vanethos.nearbyservice.di.ServiceContext
import com.vanethos.nearbyservice.di.modules.services.NearbyScanServiceModule
import com.vanethos.nearbyservice.services.NearbyScanService
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ServiceBinding {

    @ContributesAndroidInjector(modules = arrayOf(NearbyScanServiceModule::class))
    abstract fun provideNearbyScanService() : NearbyScanService
}