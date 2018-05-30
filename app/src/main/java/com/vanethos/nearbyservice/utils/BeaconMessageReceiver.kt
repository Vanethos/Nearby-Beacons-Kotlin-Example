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
import com.google.gson.JsonSyntaxException
import com.vanethos.nearbyservice.domain.managers.BeaconManager
import com.vanethos.nearbyservice.domain.models.Beacon
import dagger.android.DaggerIntentService
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer
import io.reactivex.functions.Predicate
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class BeaconMessageReceiver : DaggerIntentService("BeaconMessageReceiver") {

    @Inject
    lateinit var beaconManager : BeaconManager

    override fun onHandleIntent(intent: Intent?) {
        intent?.let {
            Nearby.getMessagesClient(this).handleIntent(it, object : MessageListener() {
                override fun onFound(message: Message?) {
                    Timber.i("Found message via PendingIntent: " + message!!)
                    if (message != null) {
                        try {
                            val beacon = beaconManager.getBeaconFromMessage(message)
                            beaconManager.getBeaconById(beacon.id)
                                    .flatMap {
                                        if (!it) {
                                            return@flatMap Single.just(it)
                                        }
                                        throw Exception("Beacon already on DB")
                                    }
                                    .toCompletable()
                                    .andThen(beaconManager.insertBeacon(beacon))
                                    .subscribeOn(Schedulers.io())
                                    .subscribe(
                                            { Timber.d("Added beacon to db") },
                                            { Timber.e(it) }
                                    )
                        } catch (e : JsonSyntaxException) {
                            Timber.e(e)
                        }

                    }
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
