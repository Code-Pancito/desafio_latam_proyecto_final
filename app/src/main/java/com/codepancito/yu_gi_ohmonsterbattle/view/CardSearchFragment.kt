package com.codepancito.yu_gi_ohmonsterbattle.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.codepancito.yu_gi_ohmonsterbattle.R
import com.codepancito.yu_gi_ohmonsterbattle.model.db.MonsterCardEntity
import com.codepancito.yu_gi_ohmonsterbattle.viewmodel.CardSearchViewModel
import kotlinx.android.synthetic.main.card_search_fragment.*

class CardSearchFragment : Fragment(), CardSearchAdapter.OnCardClickListener {

    private val logTag = "CardSearchFragment"

    companion object {
        fun newInstance() = CardSearchFragment()
    }

    private lateinit var viewModel: CardSearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.card_search_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapter = CardSearchAdapter(listOf<MonsterCardEntity>(), this)

        recyclerView_cardList.adapter = adapter
        recyclerView_cardList.layoutManager = GridLayoutManager(context, 3)

        viewModel = ViewModelProvider(this).get(CardSearchViewModel::class.java)

        viewModel.getCardList().observe(viewLifecycleOwner, {
            adapter.updateDataSet(it)
        })
    }

    override fun onCardClick(data: MonsterCardEntity) {
        activity!!
            .supportFragmentManager
            .beginTransaction()
            .add(R.id.frameLayout_Container, AddFavouriteFragment.newInstance(data.image, data.name, data.attack.toString(), data.defense.toString(), data.id), "AddFavourite")
            .addToBackStack(null)
            .commit()
    }

}