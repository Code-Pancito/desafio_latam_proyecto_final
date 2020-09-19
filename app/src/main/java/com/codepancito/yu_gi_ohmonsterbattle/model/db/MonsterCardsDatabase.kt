package com.codepancito.yu_gi_ohmonsterbattle.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MonsterCardEntity::class, FavouriteCardEntity::class], version = 1, exportSchema = false)
abstract class MonsterCardsDatabase : RoomDatabase() {

    abstract fun monsterCardsDAO() : MonsterCardsDAO
    abstract fun favouriteCardsDAO(): FavouriteCardsDAO

    companion object {
        @Volatile
        private var INSTANCE: MonsterCardsDatabase? = null

        fun getDatabase(context: Context): MonsterCardsDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) return tempInstance
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MonsterCardsDatabase::class.java,
                    "monster_card_database")
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}