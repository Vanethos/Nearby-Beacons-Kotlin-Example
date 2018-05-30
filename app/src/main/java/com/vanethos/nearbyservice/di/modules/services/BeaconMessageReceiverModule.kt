package com.vanethos.nearbyservice.di.modules.services

import com.vanethos.nearbyservice.services.NearbyScanService
import com.vanethos.nearbyservice.utils.BeaconMessageReceiver
import dagger.Module

@Module
abstract class BeaconMessageReceiverModule : BaseServiceModule<BeaconMessageReceiver>() {
}