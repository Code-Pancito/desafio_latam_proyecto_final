package com.codepancito.yu_gi_ohmonsterbattle.model

import android.content.Context
import android.util.Log
import com.codepancito.yu_gi_ohmonsterbattle.model.db.MonsterCardEntity
import com.codepancito.yu_gi_ohmonsterbattle.model.db.MonsterCardsDatabase
import com.codepancito.yu_gi_ohmonsterbattle.model.pojo.for_remote.CardList
import com.codepancito.yu_gi_ohmonsterbattle.model.remote.MonsterCardsAPI
import com.codepancito.yu_gi_ohmonsterbattle.model.remote.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MonsterCardsRepository(context: Context) {

    private val tag = "MonsterCardsRepository"

    private val database = MonsterCardsDatabase.getDatabase(context)
    private val allMonsterCardsList = database.monsterCardsDAO().getAllMonsterCards()
    private val nonFavouriteCardsList = database.monsterCardsDAO().getNonFavouriteCards()

    fun getAllMonsterCards() = allMonsterCardsList

    fun getNonFavouriteCards() = nonFavouriteCardsList

    fun fetchDataFromServer() {
        val service = RetrofitClient.retrofitInstance().create(MonsterCardsAPI::class.java)
        val call = service.getAllCards()
        call.enqueue(object : Callback<CardList> {
            override fun onResponse(call: Call<CardList>, response: Response<CardList>) {
                if(response.body() != null && !response.body()!!.data.isNullOrEmpty()) {
                    val entityList = mutableListOf<MonsterCardEntity>()

                    response.body()!!.data.forEach {
                        entityList.add(MonsterCardEntity(it.id, it.name, it.attack, it.defense, it.cardImages[0].imageURL))
                    }

                    CoroutineScope(Dispatchers.IO).launch {
                        database.monsterCardsDAO().insertMonsterCardList(entityList)
                    }
                }else
                    Log.d(tag, "empty response :(")
            }

            override fun onFailure(call: Call<CardList>, t: Throwable) {
                @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
                Log.d(tag, t.localizedMessage)
            }
        })
    }

}