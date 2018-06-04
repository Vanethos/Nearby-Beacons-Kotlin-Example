package com.vanethos.nearbyservice.presentation.detail

import android.databinding.Bindable
import android.text.TextUtils
import com.jakewharton.rxrelay2.PublishRelay
import com.vanethos.nearbyservice.domain.models.Beacon
import com.vanethos.nearbyservice.presentation.ui._base.BaseViewModel
import com.vanethos.nearbyservice.presentation.utils.Event
import com.vanethos.nearbyservice.utils.ResourceProvider
import io.reactivex.Observable
import javax.inject.Inject

class DetailViewModel @Inject constructor(resourceProvider: ResourceProvider, var beacon: Beacon) : BaseViewModel(resourceProvider) {

    private var actionObservable : PublishRelay<Event> = PublishRelay.create()
    
    fun doAction() {
        actionObservable.accept(Event())
    }

    @Bindable
    fun getActionUrl() : String? {
        return beacon?.actionUrl
    }

    @Bindable
    fun getButtonVisibility() : Boolean? {
        if (beacon != null) {
            return !TextUtils.isEmpty(beacon!!.actionMessage) && !TextUtils.isEmpty(beacon!!.actionUrl)
        }
        return false
    }

    @Bindable
    fun getTitle() : String? {
        return beacon?.title
    }

    @Bindable
    fun getImageUrl() : String? {
        return beacon?.imageUrl
    }

    @Bindable
    fun getMessage() : String? {
        return beacon?.message
    }

    @Bindable
    fun getActionMessage() : String? {
        return beacon?.actionMessage
    }

    fun observeAction() : Observable<Event> = actionObservable
}