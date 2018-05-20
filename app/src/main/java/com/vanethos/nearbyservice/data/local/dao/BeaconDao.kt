package com.vanethos.nearbyservice.data.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.vanethos.nearbyservice.data.local.entities.BeaconEntity


@Dao
interface BeaconDao {
    @Query("SELECT *")
    fun getAll(): List<BeaconEntity>


    @Insert
    fun insertAll(vararg beaconEntity: BeaconEntity)

    @Delete
    fun delete(beaconEntity: BeaconEntity)

}