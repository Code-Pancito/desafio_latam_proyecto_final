package com.codepancito.yu_gi_ohmonsterbattle.model.pojo

import com.google.gson.annotations.SerializedName

data class Card(val id: Int,
                val name: String,
                @SerializedName("atk")
                val attack: Int,
                @SerializedName("def")
                val defense: Int,
                @SerializedName("card_images")
                val cardImages: List<CardImage>)