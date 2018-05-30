package com.vanethos.nearbyservice.data.local.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.vanethos.nearbyservice.data.local.entities.BeaconEntity
import io.reactivex.Flowable
import io.reactivex.Single


@Dao
interface BeaconDao {
    @Query("SELECT * FROM beacons")
    fun getAll(): LiveData<List<BeaconEntity>>

    @Query("SELECT CASE WHEN EXISTS (SELECT * FROM beacons WHERE beacon_id = :id ) " +
            "THEN CAST(1 AS BIT)" +
            "ELSE CAST(0 AS BIT) END")
    fun getBeacon(id : Int): Single<Boolean>


    @Insert
    fun insertAll(vararg beaconEntity: BeaconEntity)

    @Query("DELETE FROM beacons")
    fun deleteAll()

}