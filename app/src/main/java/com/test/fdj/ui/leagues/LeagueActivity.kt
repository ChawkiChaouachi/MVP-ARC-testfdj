package com.test.fdj.ui.leagues

import android.os.Bundle
import com.test.clientthesportsdb.model.Leagues
import com.test.fdj.R
import com.test.fdj.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LeagueActivity : BaseActivity() ,LeagueContract.View{

     @Inject
     lateinit var presenterLeague: LeaguesPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_league)

    }

    override fun initializePresenter() {
        presenterLeague.setView(this)
        super.presenter = presenterLeague
    }

    override fun initializeLeaguesList(leagues: Leagues) {

    }

    override fun onResume() {
        super.onResume()
        presenterLeague.getLeague()
    }
}