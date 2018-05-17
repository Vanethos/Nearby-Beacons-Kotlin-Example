package com.vanethos.nearbyservice.utils

import com.google.android.gms.nearby.connection.Connections
import com.google.android.gms.nearby.messages.BleSignal
import com.google.android.gms.nearby.messages.Distance
import com.google.android.gms.nearby.messages.Message
import com.google.android.gms.nearby.messages.MessageListener
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NearbyMessagesUtils @Inject constructor(){
    fun provideMessageListener() : MessageListener {
       return object : MessageListener() {
           override fun onFound(p0: Message?) {
               if (p0 != null) {
                   Timber.d("Found beacon ${String(p0.content)}")
               }
           }
       }
    }
}