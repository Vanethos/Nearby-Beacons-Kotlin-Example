package com.vanethos.nearbyservice.di.modules.activities

import android.content.Context
import android.support.v7.app.AppCompatActivity
import com.vanethos.nearbyservice.di.ActivityContext
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * Created by goncalopalma on 16/04/2018.
 */
@Module
abstract class BaseActivityModule<in A : AppCompatActivity> {

    @Binds
    abstract fun activity(activity: A): AppCompatActivity

    @Module
    companion object {
        @JvmStatic
        @Provides
        @Named("ActivityContext")
        fun provideContext(activity: AppCompatActivity): Context = activity
    }
}