package com.codepancito.yu_gi_ohmonsterbattle.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.codepancito.yu_gi_ohmonsterbattle.model.MonsterCardsRepository

class DeckSelectionViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MonsterCardsRepository(application)
    private val favourites = repository.getFavouriteCards()

    fun getFavouriteCards() = favourites

}