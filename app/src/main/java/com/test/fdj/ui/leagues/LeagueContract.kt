package com.test.fdj.ui.leagues

import com.test.clientthesportsdb.model.Leagues
import com.test.fdj.modelapp.STeam
import com.test.fdj.modelapp.sLeague
import com.test.fdj.ui.base.BaseView

class LeagueContract {
    interface View : BaseView {
        fun initializeLeaguesList(leagues : List<sLeague>)
        fun publishTeamsByLeagues(teams : List<STeam>)
        fun showError()
        fun retry(isLeagueRequest : Boolean,idLeague: String)
        fun showLoading()
        fun hideLoading()
    }
    interface Presenter {
        fun getLeague()
        fun getTeamsByLeague(idLeague : String)

    }
}