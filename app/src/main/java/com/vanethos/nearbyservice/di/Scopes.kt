package com.vanethos.nearbyservice.di

import javax.inject.Scope

/**
 * Created by goncalopalma on 16/04/2018.
 */
@Scope
@Retention
annotation class ActivityScope

@Scope
@Retention
annotation class FragmentScope

@Scope
@Retention
annotation class ChildFragmentScope

@Scope
@Retention
annotation class ServiceScope

@Scope
@Retention
annotation class BroadcastReceiverScope