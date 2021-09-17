package com.test.fdj.ui.leagues

import com.test.clientthesportsdb.respository.SuccessResource
import com.test.fdj.modelapp.STeam
import com.test.fdj.modelapp.sLeague
import com.test.fdj.ui.base.Presenter
import com.test.fdj.usescases.GetAllLeaguesUseCase
import com.test.fdj.usescases.GetAllTeamsByLeagueUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class LeaguesPresenter @Inject constructor(private val getAllLeaguesUseCase: GetAllLeaguesUseCase,
                                           private val getAllTeamsByLeagueUseCase: GetAllTeamsByLeagueUseCase,
                                           override val coroutineContext: CoroutineContext
):LeagueContract.Presenter,
    CoroutineScope, Presenter<LeagueContract.View>() {


    override fun getLeague() {
        launch(Dispatchers.Main) {
            try {
                getAllLeaguesUseCase.invoke().collect {
                    when (it) {
                        is SuccessResource<List<sLeague>> -> {
                            getView()?.initializeLeaguesList(it.value)

                        }
                        else ->{

                        }
                    }

                }


            } catch (e: Exception) {

            }
        }
    }

    override fun getTeamsByLeague(idLeague: String) {
        launch(Dispatchers.Main) {
            try {
                getAllTeamsByLeagueUseCase.invoke(idLeague).collect {
                    when (it) {
                        is SuccessResource<List<STeam>> -> {
                            getView()?.publishTeamsByLeagues(it.value)

                        }
                        else ->{

                        }
                    }

                }


            } catch (e: Exception) {

            }
        }
    }


}