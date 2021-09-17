package com.test.clientthesportsdb.network

import android.util.Log
import com.test.clientthesportsdb.model.Leagues
import com.test.clientthesportsdb.model.Teams
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface TheSportApi {

    @GET("/api/v1/json/1/all_leagues.php")
    suspend fun getAllLeagues(): Leagues

    @GET("/api/v1/json/1/lookup_all_teams.php")
    suspend fun getAllTeamsByLeagues(@Query("id",encoded = true) id: String): Teams

}

fun createClientWebService(address: String): TheSportApi {


    //Add logger interceptor to get trace of Http request
    val interceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
        override fun log(message: String) {
            Log.d("HTTP", message)
        }
    })

    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

    val client = OkHttpClient.Builder()
        .connectionSpecs(
            listOf(
                ConnectionSpec.CLEARTEXT,
                ConnectionSpec.MODERN_TLS,
                ConnectionSpec.COMPATIBLE_TLS
            )
        )
        .addInterceptor(interceptor).build()

    val retrofit = Retrofit.Builder()
        .baseUrl(address)
        .client(client)
        //Add moshi converter
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    return retrofit.create(TheSportApi::class.java)
}

const val THE_SPORT_SDB_ACCESS_POINT_NETWORK_ADDRESS = "https://www.thesportsdb.com/"
