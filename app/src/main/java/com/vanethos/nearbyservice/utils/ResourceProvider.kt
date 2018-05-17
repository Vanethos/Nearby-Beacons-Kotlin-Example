package com.vanethos.nearbyservice.utils

import android.app.Application
import android.content.Context
import android.support.annotation.StringRes
import javax.inject.Inject

class ResourceProvider @Inject constructor(var context: Application) {
    fun getString(@StringRes id : Int) : String {
        return context.getString(id)
    }
}