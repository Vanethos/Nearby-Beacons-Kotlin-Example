package com.vanethos.nearbyservice.presentation.ui._base

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import com.android.databinding.library.baseAdapters.BR
import com.google.android.gms.nearby.Nearby
import com.vanethos.nearbyservice.presentation.utils.ViewModelFactory
import com.vanethos.nearbyservice.utils.ResourceProvider
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

abstract class BaseActivity<ViewModel : BaseViewModel, Binding : ViewDataBinding> : DaggerAppCompatActivity() {
    @Inject lateinit var resourceProvider: ResourceProvider

    // ViewModel
    @Inject protected lateinit var viewModelFactory: ViewModelFactory<ViewModel>
    lateinit var viewModel: ViewModel
        private set
    lateinit var binding : Binding
        private set

    val compositeDisposable = CompositeDisposable()

    open fun initializeObservables() {}
    open fun initArguments(bundle: Bundle?) {}
    open fun initializeViews(binding: ViewDataBinding?) {}
    open fun setupNavigation() {}
    abstract fun getLayoutId() : Int
    abstract fun getViewModelClass() : Class<ViewModel>


    override fun onCreate(savedInstanceState: Bundle?) {
        initArguments(intent.extras)
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModelClass())
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        binding.setVariable(BR.viewModel, viewModel)
        initializeViews(binding)
        setupNavigation()
    }

    override fun onStart() {
        super.onStart()
        initializeObservables()
    }

    override fun onPause() {
        super.onPause()
        compositeDisposable.clear()
    }

    fun addDisposable(d : Disposable) = compositeDisposable.add(d)
}