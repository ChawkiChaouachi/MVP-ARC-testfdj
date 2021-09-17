package com.test.fdj.ui.leagues

import android.content.Context
import android.content.Intent
import android.icu.lang.UCharacter
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
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
import androidx.recyclerview.widget.StaggeredGridLayoutManager
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
            AdapterView.OnItemClickListener { _, arg1, i, l ->
                presenterLeague.getTeamsByLeague(leagues[i].idLeague)
                val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputManager.hideSoftInputFromWindow(arg1.applicationWindowToken, 0)
            }
    }

    override fun publishTeamsByLeagues(teams :List<STeam>) {

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view_teams)
        recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
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