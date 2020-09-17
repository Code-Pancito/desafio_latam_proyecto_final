package com.codepancito.yu_gi_ohmonsterbattle.model.remote

import com.codepancito.yu_gi_ohmonsterbattle.model.pojo.for_remote.CardList
import retrofit2.Call
import retrofit2.http.GET

interface MonsterCardsAPI {
    @GET("cardinfo.php?level=gte1/")
    fun getAllCards(): Call<CardList>
}