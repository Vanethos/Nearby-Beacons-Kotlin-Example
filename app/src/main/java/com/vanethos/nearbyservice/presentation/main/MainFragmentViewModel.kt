package com.vanethos.nearbyservice.presentation.main

import com.vanethos.nearbyservice.presentation.ui._base.BaseViewModel
import com.vanethos.nearbyservice.utils.ResourceProvider
import javax.inject.Inject

class MainFragmentViewModel @Inject constructor(resourceProvider: ResourceProvider) : BaseViewModel(resourceProvider) {
    fun getInvalidText() : String {
        return "NOT OK"
    }
}