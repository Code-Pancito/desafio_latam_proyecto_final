package com.codepancito.yu_gi_ohmonsterbattle.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.codepancito.yu_gi_ohmonsterbattle.model.MonsterCardsRepository
import com.codepancito.yu_gi_ohmonsterbattle.model.logic.Battle

class BattleViewModel(application: Application): AndroidViewModel(application) {

    private val repository = MonsterCardsRepository(application)

    fun createBattle(playerDeckIDs: List<Int>): Battle {
        val playerDeck = repository.getCardListFromIDs(playerDeckIDs)
        val opponentDeck = repository.getRandomCardList(5)

        return repository.createBattle(playerDeck, opponentDeck)
    }

}