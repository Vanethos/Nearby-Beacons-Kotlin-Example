package com.vanethos.nearbyservice.data.local.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.threeten.bp.LocalDateTime

/*
    Beacon Object:
    {
        "id" : 0,
        "title" : "Beacon #1",
        "imageUrl" : "https://images.pexels.com/photos/261628/pexels-photo-261628.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
        "message" : "This is going to be a really nifty message that you can put on your beacon as an attachment",
        "actionMessage" : "Beacon Docs",
        "actionUrl" : "https://developers.google.com/nearby/messages/android/get-started"
    }

 */

@Entity(tableName = "beacons")
data class BeaconEntity(
    @PrimaryKey(autoGenerate = true) var uid: Int = 0,
    @ColumnInfo(name = "beacon_id") var id : Int = -1,
    @ColumnInfo(name = "title") var title : String = "",
    @ColumnInfo(name = "image_url") var imageUrl : String = "",
    @ColumnInfo(name = "message") var message : String = "",
    @ColumnInfo(name = "action_url") var actionUrl : String = "",
    @ColumnInfo(name = "action_message") var actionMessage : String = "",
    @ColumnInfo(name = "date_added") var dateAdded : LocalDateTime = LocalDateTime.now()
)