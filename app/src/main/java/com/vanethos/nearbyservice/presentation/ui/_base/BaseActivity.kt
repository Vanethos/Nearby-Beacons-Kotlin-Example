package com.vanethos.nearbyservice.presentation.ui._base

import com.google.android.gms.nearby.Nearby
import com.vanethos.nearbyservice.utils.ResourceProvider
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {
    @Inject lateinit var resourceProvider: ResourceProvider
    val compositeDisposable = CompositeDisposable()

    fun addDisposable(d : Disposable) = compositeDisposable.add(d)

    override fun onPause() {
        super.onPause()
        compositeDisposable.clear()
    }
}