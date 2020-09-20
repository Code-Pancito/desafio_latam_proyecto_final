package com.codepancito.yu_gi_ohmonsterbattle.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.codepancito.yu_gi_ohmonsterbattle.model.MonsterCardsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavouriteCardsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MonsterCardsRepository(application)
    private val favouriteCards = repository.getFavouriteCards()

    fun getFavouriteCards() = favouriteCards

    fun emptyFavouriteList() = CoroutineScope(Dispatchers.IO).launch {
        repository.deleteAllFavourites()
    }
}