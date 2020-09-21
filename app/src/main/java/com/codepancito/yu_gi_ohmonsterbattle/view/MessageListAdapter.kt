package com.codepancito.yu_gi_ohmonsterbattle.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codepancito.yu_gi_ohmonsterbattle.R
import kotlinx.android.synthetic.main.item_message.view.*

class MessageListAdapter(private var messagesDataSet: List<String>):
    RecyclerView.Adapter<MessageListAdapter.MessageListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_message, parent, false)
        return MessageListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return messagesDataSet.size
    }

    override fun onBindViewHolder(holder: MessageListViewHolder, position:Int) {
        val message = messagesDataSet[position]
        holder.textViewMessage.text = message

    }

    // Función para actualizar el dataset dentro de un observer
    fun updateDataSet(newDataSet: List<String>) {
        messagesDataSet = newDataSet
        notifyDataSetChanged()
    }

    inner class MessageListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var textViewMessage: TextView = itemView.textView_message
    }

}