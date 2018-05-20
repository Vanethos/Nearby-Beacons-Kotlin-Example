package com.vanethos.nearbyservice.presentation

import android.annotation.SuppressLint
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import com.jakewharton.rxrelay2.BehaviorRelay
import com.vanethos.nearbyservice.R
import com.vanethos.nearbyservice.presentation.ui._base.BaseViewModel
import com.vanethos.nearbyservice.presentation.utils.Event
import com.vanethos.nearbyservice.utils.NetworkConnectivityManager
import com.vanethos.nearbyservice.utils.PermissionsUtils
import com.vanethos.nearbyservice.utils.ResourceProvider
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscriber
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(
        resourceProvider: ResourceProvider,
        permissionsManager: PermissionsUtils,
        connectivityManager: NetworkConnectivityManager
        ) : BaseViewModel(resourceProvider) {
    init {
        Observable.combineLatest(
                    permissionsManager.ensurePermissions(),
                    connectivityManager.observeInternetState(),
                    BiFunction<Boolean, Boolean, Boolean> { permission, internet -> permission && internet }
                )
                .doOnSubscribe({ this::addDisposable })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { event -> startScanObservable.accept(event) },
                        { error -> Timber.e(error) }
                )
    }

    private val startScanObservable = BehaviorRelay.create<Boolean>()

    fun getInvalidText() : String {
        return "NOT OK"
    }

    fun getValidText() : String {
        return "OK"
    }

    fun observeStartScan() : Observable<Boolean> {
        return startScanObservable
    }
}