package com.vanethos.nearbyservice.presentation.offline

import com.vanethos.nearbyservice.R
import com.vanethos.nearbyservice.databinding.FragmentOfflineBinding
import com.vanethos.nearbyservice.presentation.ui._base.BaseFragment

class OfflineFragment : BaseFragment<OfflineViewModel, FragmentOfflineBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_offline
    }

    override fun getViewModelClass(): Class<OfflineViewModel> {
        return OfflineViewModel::class.java
    }
}