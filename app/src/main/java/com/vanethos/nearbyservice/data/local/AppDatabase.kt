package com.vanethos.nearbyservice.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.vanethos.nearbyservice.data.local.entities.BeaconEntity
import com.vanethos.nearbyservice.data.local.converters.Converters
import com.vanethos.nearbyservice.data.local.dao.BeaconDao

@Database(entities = [BeaconEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun BeaconDao() : BeaconDao
}