package com.vanethos.nearbyservice.data.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.vanethos.nearbyservice.data.local.entities.BeaconEntity
import io.reactivex.Flowable


@Dao
interface BeaconDao {
    @Query("SELECT * FROM beacons")
    fun getAll(): Flowable<BeaconEntity>


    @Insert
    fun insertAll(vararg beaconEntity: BeaconEntity)

    @Query("DELETE FROM beacons")
    fun deleteAll()

}