package com.vanethos.nearbyservice.presentation.ui._base

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.databinding.library.baseAdapters.BR
import com.vanethos.nearbyservice.presentation.utils.ViewModelFactory
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

abstract class BaseFragment<ViewModel : BaseViewModel, Binding : ViewDataBinding> : DaggerFragment()  {
    @Inject
    protected lateinit var viewModelFactory: ViewModelFactory<ViewModel>
    lateinit var viewModel: ViewModel
        private set
    lateinit var binding : Binding
        private set

    val compositeDisposable = CompositeDisposable()

    open fun initializeObservables() {}
    open fun initializeViews(binding: Binding?) {}
    abstract fun getLayoutId() : Int
    abstract fun getViewModelClass() : Class<ViewModel>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModelClass())
        binding.setVariable(BR.viewModel, viewModel)
        initializeViews(binding)
    }

    override fun onStart() {
        super.onStart()
        initializeObservables()
    }

    fun addDisposable(d : Disposable) = compositeDisposable.add(d)
}