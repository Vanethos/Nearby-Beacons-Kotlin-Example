package com.vanethos.nearbyservice.di.modules.services.builder

import com.vanethos.nearbyservice.di.ServiceContext
import com.vanethos.nearbyservice.di.modules.services.BeaconMessageReceiverModule
import com.vanethos.nearbyservice.di.modules.services.NearbyScanServiceModule
import com.vanethos.nearbyservice.services.NearbyScanService
import com.vanethos.nearbyservice.utils.BeaconMessageReceiver
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ServiceBinding {
    @ContributesAndroidInjector(modules = arrayOf(NearbyScanServiceModule::class))
    abstract fun provideNearbyScanService() : NearbyScanService

    @ContributesAndroidInjector(modules = arrayOf(BeaconMessageReceiverModule::class))
    abstract fun provideBeaconMessageReceiver() : BeaconMessageReceiver
}