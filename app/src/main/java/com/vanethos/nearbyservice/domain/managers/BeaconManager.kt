package com.vanethos.nearbyservice.domain.managers

import android.arch.core.util.Function
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import com.vanethos.nearbyservice.data.local.AppDatabase
import com.vanethos.nearbyservice.domain.mappers.BeaconMapper
import com.vanethos.nearbyservice.domain.models.Beacon
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class BeaconManager @Inject constructor(val appDatabase: AppDatabase) {

    fun getBeacons() : LiveData<List<Beacon>> {
        return Transformations.map(
                appDatabase.BeaconDao().getAll(), { beacon -> BeaconMapper.Instance.mapListToDomain(beacon) }
        )
    }

    fun deleteAllBeacons() : Completable {
        return Completable.fromAction(
                { appDatabase.BeaconDao().deleteAll() }
        )
    }

    fun insertBeacon(beacon: Beacon) : Completable {
        return Completable.fromAction(
                {appDatabase.BeaconDao().insertAll(BeaconMapper.Instance.mapToData(beacon))}
        )
    }
}