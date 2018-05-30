package com.vanethos.nearbyservice.presentation.main

import android.annotation.SuppressLint
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


    @SuppressLint("CheckResult")
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