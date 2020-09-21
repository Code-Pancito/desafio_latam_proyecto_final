package com.codepancito.yu_gi_ohmonsterbattle.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.codepancito.yu_gi_ohmonsterbattle.R
import com.codepancito.yu_gi_ohmonsterbattle.model.db.MonsterCardEntity
import com.codepancito.yu_gi_ohmonsterbattle.model.logic.Battle
import com.codepancito.yu_gi_ohmonsterbattle.viewmodel.BattleViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_battle.*
import kotlinx.coroutines.*
import java.util.ArrayList

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PLAYER_DECK_IDS = "deck"

/**
 * A simple [Fragment] subclass.
 * Use the [BattleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BattleFragment : Fragment() {

    private var playerDeckIDs = mutableListOf<Int>()

    private lateinit var playerDeck: List<MonsterCardEntity>
    private lateinit var opponentDeck: List<MonsterCardEntity>

    private lateinit var battle: Battle
    private lateinit var battleLoad: Deferred<Battle>

    private lateinit var viewModel: BattleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            it.getIntegerArrayList(ARG_PLAYER_DECK_IDS)!!.forEach { id ->
                playerDeckIDs.add(id)
            }
        }
        battleLoad = CoroutineScope(Dispatchers.IO).async {
            viewModel.createBattle(playerDeckIDs)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_battle, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(BattleViewModel::class.java)

        val messageListAdapter = MessageListAdapter(listOf())

        recyclerView_Battle_Messages.adapter = messageListAdapter
        recyclerView_Battle_Messages.layoutManager = LinearLayoutManager(context)

        battle = viewModel.createBattle(playerDeckIDs)

        playerDeck = battle.playerDeck
        opponentDeck = battle.opponentDeck

        battle.playerPoints.observe(viewLifecycleOwner, {
            textView_PlayerScore.text = it.toString()
        })

        battle.opponentPoints.observe(viewLifecycleOwner, {
            textView_OpponentScore.text = it.toString()
        })

        battle.deckPosition.observe(viewLifecycleOwner, {
            if (it > -1) {
                Picasso.get().load(playerDeck[it].image).into(imageView_PlayerCardImage)
                Picasso.get().load(opponentDeck[it].image).into(imageView_OpponentCardImage)
            }
        })

        battle.messageList.observe(viewLifecycleOwner, {
            messageListAdapter.updateDataSet(it)
        })

        button_start_turn.setOnClickListener {
            battle.startTurn()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment BattleFragment.
         */
        @JvmStatic
        fun newInstance(playerDeckIDs: ArrayList<Int>) =


            BattleFragment().apply {
                arguments = Bundle().apply {
                    putIntegerArrayList(ARG_PLAYER_DECK_IDS, playerDeckIDs)
                }
            }
    }
}