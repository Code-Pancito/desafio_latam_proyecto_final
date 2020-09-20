package com.codepancito.yu_gi_ohmonsterbattle.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.codepancito.yu_gi_ohmonsterbattle.R
import com.codepancito.yu_gi_ohmonsterbattle.viewmodel.DeckSelectionViewModel
import kotlinx.android.synthetic.main.deck_selection_fragment.*

class DeckSelectionFragment : Fragment() {

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
    }

}