package com.codepancito.yu_gi_ohmonsterbattle.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.codepancito.yu_gi_ohmonsterbattle.model.MonsterCardsRepository
import com.codepancito.yu_gi_ohmonsterbattle.model.db.FavouriteCardEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RemoveFavouriteViewModel(application: Application): AndroidViewModel(application) {

    private val repository = MonsterCardsRepository(application)

    fun removeCardFromFavourites(favourite: FavouriteCardEntity) = CoroutineScope(Dispatchers.IO).launch {
        repository.removeCardFromFavourites(favourite)
    }
}