package com.vanethos.nearbyservice.presentation.ui._base

import android.arch.lifecycle.ViewModel
import com.vanethos.nearbyservice.utils.ResourceProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel constructor(
        protected val resourceProvider: ResourceProvider
        ) : ViewModel() {
    init {
        initializeViewModel()
    }



    open fun initializeViewModel() {}

    val compositeDisposable = CompositeDisposable()
    fun addDisposable(d : Disposable) = compositeDisposable.add(d)

     override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}