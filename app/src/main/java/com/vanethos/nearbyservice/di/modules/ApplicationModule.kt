package com.vanethos.nearbyservice.di

import android.app.Application
import android.content.Context
import com.vanethos.nearbyservice.NearbyApp
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by goncalopalma on 16/04/2018.
 */
@Module
abstract class ApplicationModule {
    @Module
    companion object {
        @JvmStatic
        @Provides
        fun provideContext(application: Application): Context = application
    }
}