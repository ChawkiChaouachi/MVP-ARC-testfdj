package com.test.fdj.ui.leagues

import com.test.clientthesportsdb.model.Leagues
import com.test.fdj.modelapp.sLeague
import com.test.fdj.ui.base.BaseView

class LeagueContract {
    interface View : BaseView {
        fun initializeLeaguesList(leagues : List<sLeague>)
    }
    interface Presenter {
        fun getLeague()

    }
}