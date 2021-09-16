package com.test.fdj.usescases

import com.test.clientthesportsdb.respository.ErrorResource
import com.test.clientthesportsdb.respository.Repository
import com.test.clientthesportsdb.respository.Resource
import com.test.clientthesportsdb.respository.SuccessResource
import com.test.fdj.modelapp.sLeague
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface GetAllLeaguesUseCase {
    operator fun invoke(): Flow<Resource<List<sLeague>>>
}

class GetAllLeaguesUseCaseImpl @Inject constructor(
    private val repository: Repository
) :
    GetAllLeaguesUseCase {
    override fun invoke(): Flow<Resource<List<sLeague>>> = flow {
        try {
            val response = repository.requestAllLeagues()
            if (response.value != null) {
                emit(SuccessResource(sLeagueMapper(response.value!!.leagues)))
            } else {
                emit(ErrorResource())
            }

        } catch (e: Exception) {
            emit(ErrorResource())
        }
    }
}