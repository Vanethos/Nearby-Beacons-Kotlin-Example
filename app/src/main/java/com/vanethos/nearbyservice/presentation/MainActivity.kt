package com.vanethos.nearbyservice.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import com.tbruyelle.rxpermissions2.RxPermissions
import com.vanethos.nearbyservice.R
import com.vanethos.nearbyservice.databinding.ActivityMainBinding
import com.vanethos.nearbyservice.presentation.ui._base.BaseActivity
import com.vanethos.nearbyservice.utils.PermissionsUtils
import com.vanethos.nearbyservice.utils.ResourceProvider
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {



    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getViewModelClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    @SuppressLint("CheckResult")
    override fun initializeObservables() {
        viewModel.observeStartScan()
                .doOnSubscribe( {this::addDisposable} )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            proceed: Boolean ->
                                if (proceed) {
                                    Timber.e("--- ok")
                                    initializeScan()
                                    binding.textView.text = viewModel.getValidText()
                                } else {
                                    Timber.e("--- error")
                                    Toast.makeText(this, "Please turn permission ON in settings", Toast.LENGTH_LONG)
                                    binding.textView.text = viewModel.getInvalidText()
                                }
                         } ,
                        {e -> Timber.e(e)}
                )
    }

    private fun initializeScan() {

    }
}
