package com.codepancito.yu_gi_ohmonsterbattle.model.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MonsterCardsDAO {

    @Query("SELECT * FROM monster_card")
    fun getAllMonsterCards(): LiveData<List<MonsterCardEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMonsterCardList(data: List<MonsterCardEntity>)
}