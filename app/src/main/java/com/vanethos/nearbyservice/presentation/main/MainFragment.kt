package com.vanethos.nearbyservice.presentation.main

import android.arch.lifecycle.Observer
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import androidx.navigation.fragment.findNavController
import com.vanethos.nearbyservice.BUNDLE_BEACON
import com.vanethos.nearbyservice.R
import com.vanethos.nearbyservice.databinding.FragmentMainBinding
import com.vanethos.nearbyservice.databinding.FragmentOfflineBinding
import com.vanethos.nearbyservice.domain.models.Beacon
import com.vanethos.nearbyservice.presentation.ui._base.BaseFragment
import kotlinx.android.synthetic.main.fragment_main.view.*

class MainFragment : BaseFragment<MainFragmentViewModel, FragmentMainBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_main
    }

    override fun getViewModelClass(): Class<MainFragmentViewModel> {
        return MainFragmentViewModel::class.java
    }

    override fun initializeViews(binding: FragmentMainBinding?) {
        val beaconAdapter = BeaconsAdapter(ArrayList<Beacon>(),
                {beacon ->
                    run {
                        var bundle = Bundle()
                        bundle.putParcelable(BUNDLE_BEACON, beacon)
                        findNavController().navigate(R.id.action_mainFragment_to_detailFragment, bundle)
                    }
                } )
        viewModel.getBeacons().observe(this, Observer { beacons -> beaconAdapter.setData(beacons)  })
        val layoutManager = LinearLayoutManager(context)
        binding?.mainRecyclerView?.adapter = beaconAdapter
        binding?.mainRecyclerView?.layoutManager = layoutManager
    }
}