package com.vanethos.nearbyservice.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.widget.Toast
import androidx.navigation.findNavController
import com.vanethos.nearbyservice.R
import com.vanethos.nearbyservice.databinding.ActivityMainBinding
import com.vanethos.nearbyservice.presentation.ui._base.BaseActivity
import com.vanethos.nearbyservice.services.NearbyScanService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import android.os.Build


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
                                    if (this.findNavController(R.id.nav_host_fragment).currentDestination.id == R.id.offlineFragment) {
                                        this.findNavController(R.id.nav_host_fragment).popBackStack()
                                    }
                                } else {
                                    this.findNavController(R.id.nav_host_fragment).navigate(R.id.action_global_offlineFragment)
                                    Timber.e("--- error")
                                    Toast.makeText(this, "Please turn permission ON in settings", Toast.LENGTH_LONG)
                                }
                         } ,
                        {e -> Timber.e(e)}
                )
    }

    private fun initializeScan() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(Intent(this, NearbyScanService::class.java))
        } else {
            startService(Intent(this, NearbyScanService::class.java))
        }
    }

    override fun onSupportNavigateUp()
            = findNavController(R.id.nav_host_fragment).navigateUp()
}
