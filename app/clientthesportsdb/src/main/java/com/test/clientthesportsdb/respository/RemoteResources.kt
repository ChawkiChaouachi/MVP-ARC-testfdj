package com.test.clientthesportsdb.respository

import com.test.clientthesportsdb.model.Leagues
import com.test.clientthesportsdb.model.Teams

interface RemoteResources {
        suspend fun requestAllLeagues() : Resource<Leagues>
        suspend fun requestAllTeamsByLeagues(idLeague : String) : Resource<Teams>
}