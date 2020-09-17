package com.codepancito.yu_gi_ohmonsterbattle.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "monster_card")
data class MonsterCardEntity(@PrimaryKey
                             val id: Int,
                             val name: String,
                             val attack: Int,
                             val defense: Int,
                             val image: String)