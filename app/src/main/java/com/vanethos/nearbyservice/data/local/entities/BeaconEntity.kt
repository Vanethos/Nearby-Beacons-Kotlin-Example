package com.vanethos.nearbyservice.data.local.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.threeten.bp.LocalDateTime

@Entity(tableName = "beacons")
data class BeaconEntity(
    @PrimaryKey(autoGenerate = true) var uid: Int = 0,
    @ColumnInfo(name = "title") var title : String = "",
    @ColumnInfo(name = "image_url") var imageUrl : String = "",
    @ColumnInfo(name = "message") var message : String = "",
    @ColumnInfo(name = "action_url") var actionUrl : String = "",
    @ColumnInfo(name = "action_message") var actionMessage : String = "",
    @ColumnInfo(name = "date_added") var dateAdded : LocalDateTime = LocalDateTime.now()
)