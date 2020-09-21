package com.codepancito.yu_gi_ohmonsterbattle.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.codepancito.yu_gi_ohmonsterbattle.R
import com.codepancito.yu_gi_ohmonsterbattle.model.db.MonsterCardEntity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_card_preview_grid.view.*

class CardSearchAdapter(private var cardsDataSet: List<MonsterCardEntity>, val onCardClickListener: OnCardClickListener):
    RecyclerView.Adapter<CardSearchAdapter.CardSearchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardSearchViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_card_preview_grid, parent, false)
        return CardSearchViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cardsDataSet.size
    }

    override fun onBindViewHolder(holder: CardSearchViewHolder, position:Int) {
        val card = cardsDataSet[position]

        Picasso.get().load(card.image).into(holder.imageViewCardPreview)
    }

    // Función para actualizar el dataset dentro de un observer
    fun updateDataSet(newDataSet: List<MonsterCardEntity>) {
        cardsDataSet = newDataSet
        notifyDataSetChanged()
    }

    inner class CardSearchViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var imageViewCardPreview: ImageView = itemView.imageView_CardPreview

        val view = itemView.setOnClickListener(this)

        override fun onClick(p0: View?) {
            onCardClickListener.onCardClick(cardsDataSet[adapterPosition])
        }
    }

    interface OnCardClickListener {
        fun onCardClick(data: MonsterCardEntity)
    }

}