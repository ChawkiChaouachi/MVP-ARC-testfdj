package com.test.fdj.usescases

import com.test.clientthesportsdb.model.League
import com.test.clientthesportsdb.model.Team
import com.test.fdj.modelapp.STeam
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

internal val steamsMapper: (List<Team>) -> List<STeam> = { teams ->
    teams.map { league ->
        with(league) {
            STeam(
                idTeam,
                strTeam,
                strTeamLogo,
                strDescriptionEN,
                strTeamBadge,
                strCountry,
                strLeague

            )
        }
    }

}