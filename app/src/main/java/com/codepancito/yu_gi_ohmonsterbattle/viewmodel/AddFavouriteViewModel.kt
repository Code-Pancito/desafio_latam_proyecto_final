package com.codepancito.yu_gi_ohmonsterbattle.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.codepancito.yu_gi_ohmonsterbattle.model.MonsterCardsRepository
import com.codepancito.yu_gi_ohmonsterbattle.model.db.FavouriteCardEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddFavouriteViewModel(application: Application): AndroidViewModel(application),
    OnFavouriteExistsListener {

    private val tag = "AddFavouriteViewModel"

    private val repository = MonsterCardsRepository(application)
    val isAddFavouriteSuccessful = MutableLiveData<Boolean>(false)
    val doesFavouriteExist = MutableLiveData<Boolean>(false)

    fun insertCardIntoFavourites(card: FavouriteCardEntity, onFavouriteExistsListener: OnFavouriteExistsListener) {
        CoroutineScope(Dispatchers.IO).launch{
            repository.insertCardIntoFavourites(card, onFavouriteExistsListener)
        }
    }

    override fun onFavouriteExists() {
        Log.d(tag, "entró a onFavouriteExists")
        doesFavouriteExist.postValue(true)
    }

    override fun onFavouriteAdded() {
        Log.d(tag, "entró a onFavouriteAdded")
        isAddFavouriteSuccessful.postValue(true)
    }

}