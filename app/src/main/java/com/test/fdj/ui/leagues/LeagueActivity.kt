package com.test.fdj.ui.leagues

import android.os.Bundle
import com.test.clientthesportsdb.model.Leagues
import com.test.fdj.R
import com.test.fdj.modelapp.sLeague
import com.test.fdj.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView


@AndroidEntryPoint
class LeagueActivity : BaseActivity() ,LeagueContract.View{

     @Inject
     lateinit var presenterLeague: LeaguesPresenter
     lateinit var localLeagues : List<sLeague>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_league)


    }

    override fun initializePresenter() {
        presenterLeague.setView(this)
        super.presenter = presenterLeague
    }

    override fun initializeLeaguesList(leagues: List<sLeague>) {
        localLeagues=leagues
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this,
            android.R.layout.simple_dropdown_item_1line, leagues.map { it.strLeague }

        )
        findViewById<AutoCompleteTextView>(R.id.leagues_autocomplete).setAdapter(adapter)
    }

    override fun onResume() {
        super.onResume()
        presenterLeague.getLeague()
    }
}