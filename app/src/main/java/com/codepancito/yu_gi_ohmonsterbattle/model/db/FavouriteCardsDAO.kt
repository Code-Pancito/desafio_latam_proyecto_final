package com.codepancito.yu_gi_ohmonsterbattle.model.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavouriteCardsDAO {

    @Query("SELECT * FROM favourite_card")
    fun getAllFavouriteCards(): LiveData<List<FavouriteCardEntity>>

    @Query("SELECT * FROM favourite_card")
    fun getFavouriteList(): List<FavouriteCardEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCardIntoFavourites(card: FavouriteCardEntity)

}