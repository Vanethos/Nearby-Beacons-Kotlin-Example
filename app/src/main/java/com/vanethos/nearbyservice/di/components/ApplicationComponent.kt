package com.vanethos.nearbyservice.di.components

import android.app.Application
import com.vanethos.nearbyservice.di.modules.activities.builder.ActivityBinding
import com.vanethos.nearbyservice.NearbyApp
import com.vanethos.nearbyservice.di.ApplicationModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by goncalopalma on 16/04/2018.
 */
@Singleton
@Component(modules = arrayOf(
        ApplicationModule::class,
        ActivityBinding::class,
        AndroidSupportInjectionModule::class
))
interface ApplicationComponent : AndroidInjector<NearbyApp> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun create(app: Application):Builder
        fun build():ApplicationComponent
    }

    override fun inject(app: NearbyApp)
}