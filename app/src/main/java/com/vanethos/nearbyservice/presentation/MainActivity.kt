package com.vanethos.nearbyservice.presentation

import android.os.Bundle
import com.tbruyelle.rxpermissions2.RxPermissions
import com.vanethos.nearbyservice.R
import com.vanethos.nearbyservice.presentation.ui._base.BaseActivity
import com.vanethos.nearbyservice.utils.PermissionsUtils
import com.vanethos.nearbyservice.utils.ResourceProvider
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : BaseActivity() {

    @Inject lateinit var string : String
    @Inject lateinit var permissionsManager: PermissionsUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView.text = resourceProvider.getString(android.R.string.VideoView_error_button)
        permissionsManager.ensurePermissions()
                .doOnSubscribe( {this::addDisposable} )
                .subscribeOn(Schedulers.io())
                .subscribe( {t: Boolean? -> Timber.d("Permission") } , {e -> Timber.e(e)})

    }


}
