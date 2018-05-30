package com.vanethos.nearbyservice.di.modules.activities.builder

import com.vanethos.nearbyservice.di.modules.activities.MainActivityModule
import com.vanethos.nearbyservice.presentation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by goncalopalma on 16/04/2018.
 */
@Module
abstract class ActivityBinding {

    @ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class))
    abstract fun bindMainActivity() : MainActivity

}