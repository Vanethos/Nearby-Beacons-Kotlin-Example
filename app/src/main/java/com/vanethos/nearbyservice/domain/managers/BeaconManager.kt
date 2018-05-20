package com.vanethos.nearbyservice.domain.managers

import com.vanethos.nearbyservice.data.local.AppDatabase
import com.vanethos.nearbyservice.domain.mappers.BeaconMapper
import com.vanethos.nearbyservice.domain.models.Beacon
import io.reactivex.Flowable
import javax.inject.Inject

class BeaconManager @Inject constructor(val appDatabase: AppDatabase) {

    fun getBeacons() : Flowable<Beacon> {
        return appDatabase.BeaconDao().getAll()
                .map { beacon -> BeaconMapper.Instance.mapToDomain(beacon) }
    }

    fun deleteAllBeacons() {
        appDatabase.BeaconDao().deleteAll()
    }

    fun insertBeacon( beacon: Beacon) {
        appDatabase.BeaconDao().insertAll(BeaconMapper.Instance.mapToData(beacon))
    }
}