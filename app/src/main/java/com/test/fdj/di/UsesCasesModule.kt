package com.test.fdj.di

import com.test.clientthesportsdb.respository.Repository
import com.test.fdj.usescases.GetAllLeaguesUseCase
import com.test.fdj.usescases.GetAllLeaguesUseCaseImpl
import com.test.fdj.usescases.GetAllTeamsByLeagueUseCase
import com.test.fdj.usescases.GetAllTeamsByLeagueUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(ActivityRetainedComponent::class)
object UsesCasesModule {

    @Provides
    fun provideAllLeaguesUseCase(repository: Repository): GetAllLeaguesUseCase {
        return GetAllLeaguesUseCaseImpl(repository)
    }
    @Provides
    fun provideAllTeamsUseCase(repository: Repository): GetAllTeamsByLeagueUseCase {
        return GetAllTeamsByLeagueUseCaseImpl(repository)
    }
    @Provides
    fun provideCoroutineContext(): CoroutineContext {
        return Dispatchers.Main
    }
}