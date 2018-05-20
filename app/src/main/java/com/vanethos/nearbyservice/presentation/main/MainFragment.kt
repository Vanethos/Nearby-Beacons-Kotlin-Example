package com.vanethos.nearbyservice.presentation.main

import com.vanethos.nearbyservice.R
import com.vanethos.nearbyservice.databinding.FragmentMainBinding
import com.vanethos.nearbyservice.databinding.FragmentOfflineBinding
import com.vanethos.nearbyservice.presentation.ui._base.BaseFragment

class MainFragment : BaseFragment<MainFragmentViewModel, FragmentMainBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_main
    }

    override fun getViewModelClass(): Class<MainFragmentViewModel> {
        return MainFragmentViewModel::class.java
    }
}