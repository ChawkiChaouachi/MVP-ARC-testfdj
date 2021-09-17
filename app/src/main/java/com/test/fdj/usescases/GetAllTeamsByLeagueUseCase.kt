package com.test.fdj.usescases

import com.test.clientthesportsdb.respository.ErrorResource
import com.test.clientthesportsdb.respository.Repository
import com.test.clientthesportsdb.respository.Resource
import com.test.clientthesportsdb.respository.SuccessResource
import com.test.fdj.modelapp.STeam
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface GetAllTeamsByLeagueUseCase {
    operator fun invoke(idLeague : String): Flow<Resource<List<STeam>>>
}


class GetAllTeamsByLeagueUseCaseImpl @Inject constructor(
    private val repository: Repository
) :
    GetAllTeamsByLeagueUseCase {
    override fun invoke(idLeague : String): Flow<Resource<List<STeam>>> = flow {
        try {
            val response = repository.requestAllTeamsByLeagues(idLeague)
            if (response.value != null) {
               emit(SuccessResource(steamsMapper(response.value!!.teams)))
            } else {
                emit(ErrorResource())
            }

        } catch (e: Exception) {
            emit(ErrorResource())
        }
    }
}