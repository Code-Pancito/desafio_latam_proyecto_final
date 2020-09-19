package com.codepancito.yu_gi_ohmonsterbattle.model

import android.content.Context
import android.util.Log
import com.codepancito.yu_gi_ohmonsterbattle.model.db.FavouriteCardEntity
import com.codepancito.yu_gi_ohmonsterbattle.model.db.MonsterCardEntity
import com.codepancito.yu_gi_ohmonsterbattle.model.db.MonsterCardsDatabase
import com.codepancito.yu_gi_ohmonsterbattle.model.pojo.for_remote.CardList
import com.codepancito.yu_gi_ohmonsterbattle.model.remote.MonsterCardsAPI
import com.codepancito.yu_gi_ohmonsterbattle.model.remote.RetrofitClient
import com.codepancito.yu_gi_ohmonsterbattle.utilities.OnFavouriteAddedListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MonsterCardsRepository(context: Context) {

    private val tag = "MonsterCardsRepository"

    private val database = MonsterCardsDatabase.getDatabase(context)
    private val cardList = database.monsterCardsDAO().getAllMonsterCards()
    private val favourites = database.favouriteCardsDAO().getAllFavouriteCards()
    private val favouriteCards = database.monsterCardsDAO().getFavouriteCards()

    fun getAllCards() = cardList

    fun getFavourites() = favourites

    fun getFavouriteCards() = favouriteCards

    suspend fun insertCardIntoFavourites(
        card: FavouriteCardEntity,
        onFavouriteAddedListener: OnFavouriteAddedListener
    ) {
        val favourites = getFavouriteList()

        if (!favourites.contains(card)) {
            database.favouriteCardsDAO().insertCardIntoFavourites(card)
            onFavouriteAddedListener.onFavouriteAdded()
        } else
            onFavouriteAddedListener.onFavouriteExists()

    }

     private fun getFavouriteList(): List<FavouriteCardEntity> {
        return database.favouriteCardsDAO().getFavouriteList()
    }

    fun fetchDataFromServer() {
        val service = RetrofitClient.retrofitInstance().create(MonsterCardsAPI::class.java)
        val call = service.getAllCards()
        call.enqueue(object : Callback<CardList> {
            override fun onResponse(call: Call<CardList>, response: Response<CardList>) {
                if (response.body() != null && !response.body()!!.data.isNullOrEmpty()) {
                    val entityList = mutableListOf<MonsterCardEntity>()

                    response.body()!!.data.forEach {
                        entityList.add(
                            MonsterCardEntity(
                                it.id,
                                it.name,
                                it.attack,
                                it.defense,
                                it.cardImages[0].imageURL
                            )
                        )
                    }

                    CoroutineScope(Dispatchers.IO).launch {
                        database.monsterCardsDAO().insertMonsterCardList(entityList)
                    }
                } else
                    Log.d(tag, "empty response :(")
            }

            override fun onFailure(call: Call<CardList>, t: Throwable) {
                @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
                Log.d(tag, t.localizedMessage)
            }
        })
    }
}
