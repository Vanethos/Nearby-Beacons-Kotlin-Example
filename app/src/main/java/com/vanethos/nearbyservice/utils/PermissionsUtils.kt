package com.vanethos.nearbyservice.utils

import android.Manifest
import android.content.Context
import android.support.v7.app.AppCompatActivity
import com.tbruyelle.rxpermissions2.RxPermissions
import com.vanethos.nearbyservice.di.ActivityContext
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject

class PermissionsUtils @Inject constructor(var activity : AppCompatActivity) {
    private val rxPermissions = RxPermissions(activity)

    fun ensurePermissions () : Observable<Boolean> = rxPermissions.request(Manifest.permission.ACCESS_FINE_LOCATION)
}