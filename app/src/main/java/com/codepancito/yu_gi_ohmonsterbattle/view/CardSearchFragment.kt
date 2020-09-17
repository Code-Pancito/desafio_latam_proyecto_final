package com.codepancito.yu_gi_ohmonsterbattle.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.codepancito.yu_gi_ohmonsterbattle.R
import com.codepancito.yu_gi_ohmonsterbattle.viewmodel.CardSearchViewModel

class CardSearchFragment : Fragment() {

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
        viewModel = ViewModelProvider(this).get(CardSearchViewModel::class.java)

        viewModel.getNonFavouriteCards().observe(viewLifecycleOwner, {
            Log.d(logTag, "size: ${it.size}")
        })
    }

}