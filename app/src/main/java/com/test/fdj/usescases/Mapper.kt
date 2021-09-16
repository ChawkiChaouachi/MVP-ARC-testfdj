package com.test.fdj.usescases

import com.test.clientthesportsdb.model.League
import com.test.fdj.modelapp.sLeague

internal val sLeagueMapper: (List<League>) -> List<sLeague> = { leagues ->
    leagues.map { league ->
        with(league) {
            sLeague(
                league.idLeague,
                league.strLeague
            )
        }
    }

}