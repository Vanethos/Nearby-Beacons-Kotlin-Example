package com.vanethos.nearbyservice.presentation.ui._base

import android.arch.lifecycle.ViewModel
import android.databinding.Observable
import com.vanethos.nearbyservice.utils.ResourceProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import android.databinding.PropertyChangeRegistry



abstract class BaseViewModel constructor(
        protected val resourceProvider: ResourceProvider
        ) : ViewModel(), Observable {
    init {
        initializeViewModel()
    }

    @Transient
    private var callbacks: PropertyChangeRegistry? = null



    open fun initializeViewModel() {}

    val compositeDisposable = CompositeDisposable()
    fun addDisposable(d : Disposable) = compositeDisposable.add(d)

     override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        synchronized(this) {
            if (callbacks == null) {
                callbacks = PropertyChangeRegistry()
            }
        }
        callbacks!!.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        synchronized(this) {
            if (callbacks == null) {
                return
            }
        }
        callbacks!!.remove(callback)
    }


    /**
     * Notifies listeners that a specific property has changed. The getter for the property
     * that changes should be marked with [Bindable] to generate a field in
     * `BR` to be used as `fieldId`.
     *
     * @param fieldId The generated BR id for the Bindable field.
     */
    fun notifyPropertyChanged(fieldId: Int) {
        synchronized(this) {
            if (callbacks == null) {
                return
            }
        }
        callbacks!!.notifyCallbacks(this, fieldId, null)
    }

    fun notifyPropertiesChanged(vararg fieldIds: Int) {
        for (fieldId in fieldIds) {
            notifyPropertyChanged(fieldId)
        }
    }
    //</editor-fold>
}