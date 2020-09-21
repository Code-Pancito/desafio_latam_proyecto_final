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

class DeckAdapter(private var deckDataSet: List<MonsterCardEntity>, private val onDeckCardClickListener: OnDeckCardClickListener):
    RecyclerView.Adapter<DeckAdapter.DeckViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeckViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_card_preview_linear_horizontal, parent, false)
        return DeckViewHolder(view)
    }

    override fun getItemCount(): Int {
        return deckDataSet.size
    }

    override fun onBindViewHolder(holder: DeckViewHolder, position:Int) {
        val deckCard = deckDataSet[position]
        Picasso.get().load(deckCard.image).into(holder.imageViewCardPreview)
    }

    // Función para actualizar el dataset dentro de un observer
    fun updateDataSet(newDataSet: List<MonsterCardEntity>) {
        deckDataSet = newDataSet
        notifyDataSetChanged()
    }

    inner class DeckViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var imageViewCardPreview: ImageView = itemView.imageView_CardPreview
        val view = itemView.setOnClickListener(this)

        override fun onClick(p0: View?) {
            onDeckCardClickListener.onDeckCardClick(deckDataSet[adapterPosition])
        }
    }

    interface OnDeckCardClickListener {
        fun onDeckCardClick(card: MonsterCardEntity)
    }

}