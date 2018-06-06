package com.vanethos.nearbyservice.presentation.detail

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.vanethos.nearbyservice.BUNDLE_BEACON
import com.vanethos.nearbyservice.R
import com.vanethos.nearbyservice.databinding.FragmentBeaconDetailBinding
import com.vanethos.nearbyservice.domain.models.Beacon
import com.vanethos.nearbyservice.presentation.MainActivity
import com.vanethos.nearbyservice.presentation.ui._base.BaseFragment
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class DetailFragment : BaseFragment<DetailViewModel, FragmentBeaconDetailBinding>(){

    lateinit var beacon : Beacon

    override fun initArgument(args: Bundle?) {
        beacon = arguments!!.getParcelable(BUNDLE_BEACON)
    }

    override fun initializeViews(binding: FragmentBeaconDetailBinding?) {
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_beacon_detail
    }

    override fun getViewModelClass(): Class<DetailViewModel> {
        return DetailViewModel::class.java
    }

    @SuppressLint("CheckResult")
    override fun initializeObservables() {
        viewModel.observeAction()
                .doOnSubscribe{this::addDisposable}
                .subscribeOn(Schedulers.io())
                .subscribe(
                        {goToWeb(viewModel.getActionUrl())},
                        {Timber.e(it)}
                )
    }

    fun goToWeb(url :String?) {
        try {
            var intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        } catch (e : ActivityNotFoundException) {
            Timber.e(e)
        }
    }

    override fun onDetach() {
        super.onDetach()
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }
}