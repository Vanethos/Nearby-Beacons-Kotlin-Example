package com.vanethos.nearbyservice.domain.managers

import android.arch.core.util.Function
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import com.google.android.gms.nearby.messages.Message
import com.google.gson.Gson
import com.vanethos.nearbyservice.data.local.AppDatabase
import com.vanethos.nearbyservice.domain.mappers.BeaconMapper
import com.vanethos.nearbyservice.domain.models.Beacon
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BeaconManager @Inject constructor(val appDatabase: AppDatabase, val gson: Gson) {

    fun getBeacons() : LiveData<List<Beacon>> {
        return Transformations.map(
                appDatabase.BeaconDao().getAll(), { beacon -> BeaconMapper.Instance.mapListToDomain(beacon) }
        )
    }

    fun getBeaconById(id : Int) : Single<Boolean> {
        return appDatabase.BeaconDao().getBeacon(id)
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

    fun getBeaconFromMessage(message : Message) : Beacon {
        Timber.d("MESSAGE:" + String(message.content))
        return gson.fromJson(String(message.content), Beacon::class.java)
    }
}