package com.test.fdj.ui.leagues

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import com.test.clientthesportsdb.model.Leagues
import com.test.fdj.R
import com.test.fdj.modelapp.sLeague
import com.test.fdj.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.fdj.modelapp.STeam


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
        findViewById<AutoCompleteTextView>(R.id.leagues_autocomplete).onItemClickListener =
            AdapterView.OnItemClickListener { _, _, i, l ->
                presenterLeague.getTeamsByLeague(leagues[i].idLeague)
            }
    }

    override fun publishTeamsByLeagues(teams :List<STeam>) {

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view_teams)
        recyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
        }
        val teamsAdapter =TeamsAdapter(baseContext) { team -> adapterOnClick(team) }
        teamsAdapter.submitList(teams)
        recyclerView.adapter =teamsAdapter
    }

    override fun onResume() {
        super.onResume()
        presenterLeague.getLeague()
    }

    private fun adapterOnClick(team: STeam) {

    }
}