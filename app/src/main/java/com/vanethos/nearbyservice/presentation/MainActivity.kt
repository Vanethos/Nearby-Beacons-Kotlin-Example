package com.vanethos.nearbyservice.presentation

import android.annotation.SuppressLint
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.findNavController
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

    }

    override fun onSupportNavigateUp()
            = findNavController(R.id.nav_host_fragment).navigateUp()
}
