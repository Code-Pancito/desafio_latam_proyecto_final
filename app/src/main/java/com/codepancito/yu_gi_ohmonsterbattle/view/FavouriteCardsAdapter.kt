package com.codepancito.yu_gi_ohmonsterbattle.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.codepancito.yu_gi_ohmonsterbattle.R
import com.codepancito.yu_gi_ohmonsterbattle.model.db.MonsterCardEntity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_card_preview.view.*

class FavouriteCardsAdapter(private var favouritesDataSet: List<MonsterCardEntity>, private var onFavouriteClickListener: OnFavouriteClickListener):
    RecyclerView.Adapter<FavouriteCardsAdapter.FavouriteCardsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteCardsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_card_preview, parent, false)
        return FavouriteCardsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return favouritesDataSet.size
    }

    override fun onBindViewHolder(holder: FavouriteCardsViewHolder, position:Int) {
        val card = favouritesDataSet[position]
        Picasso.get().load(card.image).into(holder.imageViewCardPreview)

    }

    // Función para actualizar el dataset dentro de un observer
    fun updateDataSet(newDataSet: List<MonsterCardEntity>) {
        favouritesDataSet = newDataSet
        notifyDataSetChanged()
    }

    inner class FavouriteCardsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var imageViewCardPreview: ImageView = itemView.imageView_CardPreview
        var view = itemView.setOnClickListener(this)

        override fun onClick(p0: View?) {
            onFavouriteClickListener.onFavouriteClick(favouritesDataSet[adapterPosition])
        }
    }

    interface OnFavouriteClickListener {
        fun onFavouriteClick(card: MonsterCardEntity)
    }
}