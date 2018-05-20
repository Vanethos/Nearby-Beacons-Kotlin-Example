package com.vanethos.nearbyservice.data.local.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.PrimaryKey
import org.threeten.bp.LocalDateTime

data class BeaconEntity(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "title") val title : String,
    @ColumnInfo(name = "image_url") val imageUrl : String,
    @ColumnInfo(name = "message") val message : String,
    @ColumnInfo(name = "action_url") val actionUrl : String,
    @ColumnInfo(name = "action_message") val actionMessage : String,
    @ColumnInfo(name = "date_added") val dateAdded : LocalDateTime
)