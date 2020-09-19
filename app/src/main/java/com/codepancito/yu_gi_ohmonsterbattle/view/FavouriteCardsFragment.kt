package com.codepancito.yu_gi_ohmonsterbattle.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.codepancito.yu_gi_ohmonsterbattle.R
import com.codepancito.yu_gi_ohmonsterbattle.model.db.MonsterCardEntity
import com.codepancito.yu_gi_ohmonsterbattle.viewmodel.FavouriteCardsViewModel
import kotlinx.android.synthetic.main.favourite_cards_fragment.*

class FavouriteCardsFragment : Fragment() {

    private val logTag = "FavouriteCardsFragment"

    companion object {
        fun newInstance() = FavouriteCardsFragment()
    }

    private lateinit var viewModel: FavouriteCardsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favourite_cards_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapter = FavouriteCardsAdapter(listOf<MonsterCardEntity>())
        recyclerView_favourites.adapter = adapter
        recyclerView_favourites.layoutManager = GridLayoutManager(context, 3)

        viewModel = ViewModelProvider(this).get(FavouriteCardsViewModel::class.java)

        viewModel.getFavouriteCards().observe(viewLifecycleOwner, {
            adapter.updateDataSet(it)
        })
    }

}