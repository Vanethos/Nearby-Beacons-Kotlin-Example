package com.vanethos.nearbyservice.utils

import android.app.Notification
import android.content.Context
import javax.inject.Inject
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.support.v4.app.NotificationCompat
import com.vanethos.nearbyservice.R
import com.vanethos.nearbyservice.presentation.MainActivity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Activity
import android.graphics.Color
import android.support.annotation.RequiresApi
import android.support.v4.app.NotificationManagerCompat
import com.vanethos.nearbyservice.domain.models.Beacon
import javax.inject.Singleton

@Singleton
class NotificationUtils @Inject constructor(val context: Context) {
    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannels();
        }
    }
    companion object {
        val DEFAULT_NOTIFICATION_ID = 12
        val CHANNEL_ID = "channel_id"
        val EVENT_CHANNEL_ID = "event-channel"
    }


    val channelId = "channel_id"

    fun getForegroundNotification(context: Context) : Notification {
        val notificationIntent = Intent(context, MainActivity::class.java)

        notificationIntent.setAction(Intent.ACTION_MAIN)
        notificationIntent.addCategory(Intent.CATEGORY_LAUNCHER)

        val pendingIntent = PendingIntent.getActivity(context, 0,
                notificationIntent, 0)

        return NotificationCompat.Builder(context, EVENT_CHANNEL_ID)
                .setContentTitle("MyCity Scan de Beacons")
                .setTicker("Beacon Progressive Scan")
                .setContentText("Content")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentIntent(pendingIntent)
                .setOngoing(true)
                .build()
    }

    fun sendBeaconNotification(context: Context, beacon : Beacon) {

    }

    fun sendNotification(context: Context, string: String, intent: Intent, id : Int) {
        val pendingIntent = PendingIntent.getActivity(context, 0,
                intent, 0)

        var notification = NotificationCompat.Builder(context, EVENT_CHANNEL_ID)
                .setContentTitle(string)
                .setContentText("Beacon")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentIntent(pendingIntent)
                .setOngoing(true)
                .build()

        NotificationManagerCompat.from(context).notify(id, notification)
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    fun createNotificationChannels() {
        val notificationManager = context.getSystemService(Activity.NOTIFICATION_SERVICE) as NotificationManager

        // Running Notifications
        val eventNotificationName = context.getString(R.string.notification_channel_name)
        val eventNotificationDescription = context.getString(R.string.notification_channel_description)
        val eventNotificationImportance = NotificationManager.IMPORTANCE_HIGH
        val mEventChannel = NotificationChannel(EVENT_CHANNEL_ID, eventNotificationName, eventNotificationImportance)
        mEventChannel.description = eventNotificationDescription
        mEventChannel.enableLights(true)
        mEventChannel.lightColor = Color.GREEN
        mEventChannel.enableVibration(true)
        mEventChannel.vibrationPattern = longArrayOf(100, 200, 300)


        notificationManager.createNotificationChannel(mEventChannel)
    }
}