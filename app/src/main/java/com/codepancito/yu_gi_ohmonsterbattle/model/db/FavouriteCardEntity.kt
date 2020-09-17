package com.codepancito.yu_gi_ohmonsterbattle.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_card", foreignKeys = [ForeignKey(entity = MonsterCardEntity::class, parentColumns = arrayOf("id"), childColumns = arrayOf("card_id"), onDelete =  ForeignKey.CASCADE)])
data class FavouriteCardEntity(@ColumnInfo(name = "card_id")
                               @PrimaryKey
                               val cardId: Int)