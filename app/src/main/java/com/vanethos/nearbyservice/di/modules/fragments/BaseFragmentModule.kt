package com.vanethos.nearbyservice.di.modules.fragments

import android.support.v4.app.Fragment
import dagger.Binds
import dagger.Module

@Module
abstract class BaseFragmentModule<F : Fragment> {

    @Binds
    abstract fun fragment(Fragment: F): Fragment
}
