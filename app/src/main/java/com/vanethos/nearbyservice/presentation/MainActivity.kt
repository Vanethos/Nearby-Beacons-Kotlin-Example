package com.vanethos.nearbyservice.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.databinding.ViewDataBinding
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
import android.os.Bundle
import android.text.TextUtils
import androidx.navigation.fragment.findNavController
import com.vanethos.nearbyservice.BUNDLE_BEACON
import com.vanethos.nearbyservice.BUNDLE_BEACON_MAIN
import com.vanethos.nearbyservice.domain.models.Beacon


class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    var beacon : Beacon? = null

    companion object {
        fun getIntent(context: Context, beacon: Beacon) : Intent {
            var intent = Intent(context, MainActivity::class.java)
            intent.putExtra(BUNDLE_BEACON_MAIN, beacon)
            return intent
        }
    }

    override fun initArguments(bundle: Bundle?) {
        if (bundle != null) {
            beacon = bundle.get(BUNDLE_BEACON_MAIN) as Beacon
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getViewModelClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun setupNavigation() {
        if (beacon != null) {
            var bundle = Bundle()
            bundle.putParcelable(BUNDLE_BEACON, beacon)
            this.findNavController(R.id.nav_host_fragment).navigate(R.id.action_mainFragment_to_detailFragment, bundle)
        }
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
