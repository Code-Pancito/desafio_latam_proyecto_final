package com.codepancito.yu_gi_ohmonsterbattle.model.pojo.for_remote

import com.google.gson.annotations.SerializedName

data class CardImage(@SerializedName("image_url")
                     val imageURL: String)