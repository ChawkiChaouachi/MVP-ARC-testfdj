package com.test.clientthesportsdb.di

import com.test.clientthesportsdb.network.THE_SPORT_SDB_ACCESS_POINT_NETWORK_ADDRESS
import com.test.clientthesportsdb.network.TheSportApi
import com.test.clientthesportsdb.network.createClientWebService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped


@Module
@InstallIn(ActivityRetainedComponent::class)
object TheSportApiModule {
    @Provides
    @ActivityRetainedScoped
    fun provideTheSportApiModule() : TheSportApi{
        return createClientWebService(THE_SPORT_SDB_ACCESS_POINT_NETWORK_ADDRESS)
    }
}