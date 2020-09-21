package com.codepancito.yu_gi_ohmonsterbattle.view

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.codepancito.yu_gi_ohmonsterbattle.R
import com.codepancito.yu_gi_ohmonsterbattle.model.db.MonsterCardEntity
import com.codepancito.yu_gi_ohmonsterbattle.viewmodel.FavouriteCardsViewModel
import kotlinx.android.synthetic.main.favourite_cards_fragment.*

class FavouriteCardsFragment : DialogFragment(), FavouriteCardsAdapter.OnFavouriteClickListener {

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

        val adapter = FavouriteCardsAdapter(listOf(), this, R.layout.item_card_preview_grid)
        recyclerView_favourites.adapter = adapter
        recyclerView_favourites.layoutManager = GridLayoutManager(context, 3)

        viewModel = ViewModelProvider(this).get(FavouriteCardsViewModel::class.java)

        viewModel.getFavouriteCards().observe(viewLifecycleOwner, {
            adapter.updateDataSet(it)
        })

        button_Empty_Favourites.setOnClickListener {
            onCreateDialog(savedInstanceState).show()
        }
    }

    override fun onFavouriteClick(card: MonsterCardEntity) {
        activity!!
            .supportFragmentManager
            .beginTransaction()
            .add(R.id.frameLayout_Container, RemoveFavouriteFragment.newInstance(card.image, card.name, card.attack, card.defense, card.id), "RemoveFavourite")
            .addToBackStack(null)
            .commit()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity!!.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setMessage(R.string.confirm_empty_favourites_text)
                .setPositiveButton(R.string.empty_text) { _, _ -> viewModel.emptyFavouriteList() }
                .setNegativeButton(R.string.cancel_text) { dialog, _ -> dialog.dismiss() }
            // Create the AlertDialog object and return it
            builder.create()
        }
    }

}