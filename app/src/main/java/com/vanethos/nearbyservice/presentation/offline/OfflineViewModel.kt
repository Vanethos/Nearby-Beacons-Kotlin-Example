package com.vanethos.nearbyservice.presentation.offline

import com.vanethos.nearbyservice.presentation.ui._base.BaseViewModel
import com.vanethos.nearbyservice.utils.ResourceProvider
import javax.inject.Inject

class OfflineViewModel @Inject constructor(resourceProvider: ResourceProvider) : BaseViewModel(resourceProvider) {
    fun getInvalidText() : String {
        return "NOT OK"
    }
}