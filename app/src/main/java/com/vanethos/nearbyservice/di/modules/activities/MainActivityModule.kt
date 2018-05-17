package com.vanethos.nearbyservice.di.modules.activities

import com.vanethos.nearbyservice.presentation.MainActivity
import dagger.Module
import dagger.Provides

/**
 * Created by goncalopalma on 16/04/2018.
 */
@Module
abstract class MainActivityModule : BaseActivityModule<MainActivity>() {
    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideString() : String = "cenas"
    }
}