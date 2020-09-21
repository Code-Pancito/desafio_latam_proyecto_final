package com.codepancito.yu_gi_ohmonsterbattle.model.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MonsterCardsDAO {

    @Query("SELECT * FROM monster_card ORDER BY name ASC")
    fun getAllMonsterCards(): LiveData<List<MonsterCardEntity>>

    @Query("SELECT t2.* FROM favourite_card t1 LEFT JOIN monster_card t2 WHERE t1.card_id = t2.id ORDER BY t2.name ASC")
    fun getFavouriteCards(): LiveData<List<MonsterCardEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMonsterCardList(data: List<MonsterCardEntity>)

    @Query("SELECT * FROM monster_card WHERE id IN (:cardIDList)")
    fun getCardListFromIDs(cardIDList: List<Int>): List<MonsterCardEntity>

    @Query("SELECT * FROM monster_card WHERE id IN (SELECT id FROM monster_card ORDER BY RANDOM() LIMIT :listSize)")
    fun getRandomCardList(listSize: Int): List<MonsterCardEntity>

}