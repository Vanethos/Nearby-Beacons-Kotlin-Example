package com.vanethos.nearbyservice.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.google.android.gms.nearby.Nearby
import com.google.android.gms.nearby.messages.*
import dagger.android.DaggerService
import timber.log.Timber
import android.app.PendingIntent
import android.os.Build
import com.google.android.gms.nearby.messages.SubscribeOptions
import com.vanethos.nearbyservice.utils.BeaconMessageReceiver
import com.google.android.gms.nearby.messages.NearbyPermissions
import com.google.android.gms.nearby.messages.MessagesOptions
import com.vanethos.nearbyservice.utils.NotificationUtils
import javax.inject.Inject


class NearbyScanService : DaggerService() {

    @Inject lateinit var notificationUtils: NotificationUtils

    override fun onBind(intent: Intent?): IBinder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundNotification()
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        backgroundSubscribe()
        return Service.START_STICKY
    }

    private fun startForegroundNotification() {
        startForeground(47, notificationUtils.getForegroundNotification(this))
    }

    override fun onDestroy() {
        Nearby.getMessagesClient(this).unsubscribe(getPendingIntent())
        super.onDestroy()
    }


    private fun backgroundSubscribe() {
        Timber.i("Subscribing for background updates.")
        val options = SubscribeOptions.Builder()
                .setStrategy(Strategy.BLE_ONLY)
                .build()
        //avoid permission checking since we already have ACCESS_FINE_LOCATION permission
        Nearby.getMessagesClient(this, MessagesOptions.Builder()
                .setPermissions(NearbyPermissions.BLE)
                .build())
                .subscribe(getPendingIntent(), options)
                .addOnSuccessListener { Timber.i("Connected to Nearby Messages") }
                .addOnFailureListener { Timber.e(it) }
    }

    private fun getPendingIntent(): PendingIntent {
        return PendingIntent.getService(this, 0, Intent(this, BeaconMessageReceiver::class.java),
                PendingIntent.FLAG_UPDATE_CURRENT)
    }
}