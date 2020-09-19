package com.codepancito.yu_gi_ohmonsterbattle.model.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavouriteCardsDAO {

    @Query("SELECT * FROM favourite_card")
    fun getAllFavouriteCards(): LiveData<List<FavouriteCardEntity>>

    @Query("SELECT * FROM favourite_card")
    fun getFavouriteList(): List<FavouriteCardEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCardIntoFavourites(card: FavouriteCardEntity)

    @Delete
    suspend fun removeCardFromFavourites(favourite: FavouriteCardEntity)

}