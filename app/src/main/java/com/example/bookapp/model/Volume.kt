package com.example.bookapp.model

import com.google.gson.annotations.SerializedName

class Volume(
    @SerializedName("title")
    var title: String,
    @SerializedName("authors")
    var authors: ArrayList<String>,
    @SerializedName("publishedDate")
    var date: String,
    @SerializedName("pageCount")
    var pages: String,
    @SerializedName("imageLinks")
    var images: Image,
    @SerializedName("infoLink")
    var infoLink: String
) {

}