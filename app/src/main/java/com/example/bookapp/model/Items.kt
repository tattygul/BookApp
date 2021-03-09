package com.example.bookapp.model

import com.google.gson.annotations.SerializedName

class Items(

    @SerializedName("volumeInfo")
    var volume: Volume,

)