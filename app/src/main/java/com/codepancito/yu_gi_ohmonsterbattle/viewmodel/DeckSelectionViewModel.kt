package com.codepancito.yu_gi_ohmonsterbattle.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.codepancito.yu_gi_ohmonsterbattle.model.MonsterCardsRepository
import com.codepancito.yu_gi_ohmonsterbattle.model.db.MonsterCardEntity

class DeckSelectionViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MonsterCardsRepository(application)
    private val favourites = repository.getFavouriteCards()
    private val deck = MutableLiveData<MutableList<MonsterCardEntity>>(mutableListOf())

    fun getFavouriteCards() = favourites

    fun getDeck() = deck

    fun addCardToDeck(card: MonsterCardEntity) {
        deck.value!!.add(card)
        deck.value = deck.value
    }

    fun removeCardFromDeck(card: MonsterCardEntity) {
        deck.value!!.remove(card)
        deck.value = deck.value
    }

}