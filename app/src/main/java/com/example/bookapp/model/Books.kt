package com.example.bookapp.model

import com.google.gson.annotations.SerializedName

class Books(
    @SerializedName("items")
    var items: ArrayList<Items>
) {
}