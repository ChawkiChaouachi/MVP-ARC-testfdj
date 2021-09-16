package com.test.clientthesportsdb.respository

import com.test.clientthesportsdb.model.Leagues
import com.test.clientthesportsdb.model.Teams
import com.test.clientthesportsdb.network.TheSportApi
import javax.inject.Inject

class Repository @Inject constructor(private val theSportApi: TheSportApi) : RemoteResources{
    override suspend fun requestAllLeagues(): Resource<Leagues> {
        return try {
            val response = theSportApi.getAllLeagues()
            SuccessResource(response)
        }catch (e :Exception){
            ErrorResource()
        }
    }

    override suspend fun requestAllTeamsByLeagues(idLeague :String): Resource<Teams> {
        return try {
            val response = theSportApi.getAllTeamsByLeagues(idLeague)
            SuccessResource(response)
        }catch (e :Exception){
            ErrorResource()
        }
    }
}