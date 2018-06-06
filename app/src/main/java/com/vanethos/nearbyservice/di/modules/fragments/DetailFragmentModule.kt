package com.vanethos.nearbyservice.di.modules.fragments

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.vanethos.nearbyservice.di.ActivityContext
import com.vanethos.nearbyservice.domain.models.Beacon
import com.vanethos.nearbyservice.presentation.detail.DetailFragment
import com.vanethos.nearbyservice.presentation.main.MainFragment
import com.vanethos.nearbyservice.presentation.offline.OfflineFragment
import dagger.Module
import dagger.Provides

@Module
abstract class DetailFragmentModule : BaseFragmentModule<DetailFragment>() {
    @Module
    companion object {
        @JvmStatic
        @Provides
        fun provideBeacon(detailFragment: DetailFragment) : Beacon =
                detailFragment.beacon
    }
}