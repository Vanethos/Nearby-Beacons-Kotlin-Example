package com.vanethos.nearbyservice.data.local.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.threeten.bp.LocalDateTime

@Entity(tableName = "beacons")
data class BeaconEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "title") val title : String = "",
    @ColumnInfo(name = "image_url") val imageUrl : String = "",
    @ColumnInfo(name = "message") val message : String = "",
    @ColumnInfo(name = "action_url") val actionUrl : String = "",
    @ColumnInfo(name = "action_message") val actionMessage : String = "",
    @ColumnInfo(name = "date_added") val dateAdded : LocalDateTime = LocalDateTime.now()
)