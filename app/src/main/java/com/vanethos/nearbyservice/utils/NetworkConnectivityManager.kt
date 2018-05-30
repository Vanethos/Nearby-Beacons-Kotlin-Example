package com.vanethos.nearbyservice.utils

import android.app.Application
import android.content.Context
import android.net.NetworkInfo
import com.github.pwittchen.reactivenetwork.library.rx2.Connectivity
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkConnectivityManager @Inject constructor(val context: Application) {

    fun observeInternetState() : Observable<Boolean> {
        return ReactiveNetwork.observeNetworkConnectivity(context)
                .map { connectivity: Connectivity -> !connectivity.state.equals( NetworkInfo.State.DISCONNECTED)  }
    }
}