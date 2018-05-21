package com.vanethos.nearbyservice.presentation.main

import android.arch.lifecycle.LiveData
import com.vanethos.nearbyservice.domain.managers.BeaconManager
import com.vanethos.nearbyservice.domain.models.Beacon
import com.vanethos.nearbyservice.presentation.ui._base.BaseViewModel
import com.vanethos.nearbyservice.utils.ResourceProvider
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class MainFragmentViewModel @Inject constructor(
        resourceProvider: ResourceProvider,
        val beaconManager: BeaconManager
        ) : BaseViewModel(resourceProvider) {

    fun getBeacons() : LiveData<List<Beacon>> {
        return beaconManager.getBeacons()
    }

    fun addBeacon() {
        beaconManager.insertBeacon(Beacon( "Cenas",
                "https://discordapp.com/assets/2c21aeda16de354ba5334551a883b481.png"))
                .doOnSubscribe({this::addDisposable})
                .subscribeOn(Schedulers.io())
                .subscribe(
                        { Timber.d("---- Beacon added")},
                        { error -> Timber.e(error) }
                )
    }

    fun deleteBeacons() {
        beaconManager.deleteAllBeacons()
                .doOnSubscribe({this::addDisposable})
                .subscribeOn(Schedulers.io())
                .subscribe(
                        { Timber.d("---- Beacons deleted")},
                        { error -> Timber.e(error) }
                )
    }
}