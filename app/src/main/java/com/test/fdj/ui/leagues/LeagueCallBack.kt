package com.test.fdj.ui.leagues

import com.test.fdj.modelapp.sLeague

interface LeagueCallBack {
    fun onSuccess(leagues: List<sLeague>)
    fun onFail()
}