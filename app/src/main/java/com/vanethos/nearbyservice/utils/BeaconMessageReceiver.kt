package com.vanethos.nearbyservice.utils

import android.app.IntentService
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.google.android.gms.nearby.Nearby
import com.google.android.gms.nearby.messages.BleSignal
import com.google.android.gms.nearby.messages.Distance
import com.google.android.gms.nearby.messages.Message
import com.google.android.gms.nearby.messages.MessageListener
import dagger.android.DaggerIntentService
import timber.log.Timber

class BeaconMessageReceiver : IntentService("BeaconMessageReceiver") {
    override fun onHandleIntent(intent: Intent?) {
        intent?.let {
            Nearby.getMessagesClient(this).handleIntent(it, object : MessageListener() {
                override fun onFound(message: Message?) {
                    Timber.i("Found message via PendingIntent: " + message!!)
                }

                override fun onLost(message: Message?) {
                    Timber.i("Lost message via PendingIntent: " + message!!)
                }

                override fun onDistanceChanged(p0: Message?, p1: Distance?) {
                    Timber.i("onDistanceChanged message via PendingIntent: " + p1!!)
                }

                override fun onBleSignalChanged(p0: Message?, p1: BleSignal?) {
                    Timber.i("onDistanceChanged message via PendingIntent: " + p0!!)
                }
            })
        }
    }
}
