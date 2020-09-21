package com.codepancito.yu_gi_ohmonsterbattle.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.codepancito.yu_gi_ohmonsterbattle.R
import com.codepancito.yu_gi_ohmonsterbattle.model.db.MonsterCardEntity
import com.codepancito.yu_gi_ohmonsterbattle.viewmodel.DeckSelectionViewModel
import kotlinx.android.synthetic.main.deck_selection_fragment.*

class DeckSelectionFragment : Fragment(), FavouriteCardsAdapter.OnFavouriteClickListener, DeckAdapter.OnDeckCardClickListener {

    companion object {
        fun newInstance() = DeckSelectionFragment()
    }

    private lateinit var viewModel: DeckSelectionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.deck_selection_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DeckSelectionViewModel::class.java)

        viewModel.getFavouriteCards().observe(viewLifecycleOwner, {
            if(it.size < 5) {
                textView_NotEnoughFavourites.visibility = View.VISIBLE
                constraintLayout_Deck_Container.visibility = View.INVISIBLE
            } else {
                constraintLayout_Deck_Container.visibility = View.VISIBLE
                textView_NotEnoughFavourites.visibility = View.INVISIBLE
            }
        })

        val favouritesAdapter = FavouriteCardsAdapter(listOf(), this, R.layout.item_card_preview_linear_horizontal)
        val deckAdapter = DeckAdapter(listOf(), this)

        recyclerView_DeckSelection_favourites.adapter = favouritesAdapter
        recyclerView_DeckSelection_favourites.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView_DeckSelection_Deck.adapter = deckAdapter
        recyclerView_DeckSelection_Deck.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        viewModel.getFavouriteCards().observe(viewLifecycleOwner, {
            favouritesAdapter.updateDataSet(it)
        })

        viewModel.getDeck().observe(viewLifecycleOwner, {
            deckAdapter.updateDataSet(it)
        })

        button_Add_Deck.setOnClickListener {
            if(viewModel.getDeck().value!!.size != 5)
                Toast.makeText(context, "El Mazo debe contener un total de 5 cartas", Toast.LENGTH_SHORT).show()
            else {
                var deckListIDs = arrayListOf<Int>()
                viewModel.getDeck().value!!.forEach {
                    deckListIDs.add(it.id)
                }

                activity!!
                    .supportFragmentManager
                    .beginTransaction()
                    .add(R.id.frameLayout_Container, BattleFragment.newInstance(deckListIDs), "Battle")
                    .addToBackStack(null)
                    .commit()

            }
        }
    }

    override fun onFavouriteClick(card: MonsterCardEntity) {
        when {
            viewModel.getDeck().value!!.contains(card) -> Toast.makeText(context, "Solo puedes tener una copia de cada carta por Mazo", Toast.LENGTH_SHORT).show()
            viewModel.getDeck().value!!.size >= 5 -> Toast.makeText(context, "Ya tienes 5 cartas en tu Mazo", Toast.LENGTH_SHORT).show()
            else -> viewModel.addCardToDeck(card)
        }
    }

    override fun onDeckCardClick(card: MonsterCardEntity) {
        viewModel.removeCardFromDeck(card)
    }

}