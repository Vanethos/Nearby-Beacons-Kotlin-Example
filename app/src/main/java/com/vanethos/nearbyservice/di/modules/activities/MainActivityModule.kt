package com.vanethos.nearbyservice.di.modules.activities

import com.vanethos.nearbyservice.di.modules.fragments.DetailFragmentModule
import com.vanethos.nearbyservice.di.modules.fragments.MainFragmentModule
import com.vanethos.nearbyservice.di.modules.fragments.OfflineFragmentModule
import com.vanethos.nearbyservice.presentation.MainActivity
import com.vanethos.nearbyservice.presentation.detail.DetailFragment
import com.vanethos.nearbyservice.presentation.main.MainFragment
import com.vanethos.nearbyservice.presentation.offline.OfflineFragment
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

/**
 * Created by goncalopalma on 16/04/2018.
 */
@Module
abstract class MainActivityModule : BaseActivityModule<MainActivity>() {
    @ContributesAndroidInjector(modules = arrayOf(MainFragmentModule::class))
    abstract fun provideMainFragment() : MainFragment

    @ContributesAndroidInjector(modules = arrayOf(OfflineFragmentModule::class))
    abstract fun provideOfflineFragment() : OfflineFragment

    @ContributesAndroidInjector(modules = arrayOf(DetailFragmentModule::class))
    abstract fun provideDetailFragment() : DetailFragment
}