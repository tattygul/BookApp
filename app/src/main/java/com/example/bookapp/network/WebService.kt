package com.example.bookapp.network

import com.example.bookapp.model.Books
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("books/v1/volumes")
    fun getBooks(@Query("q") query: String) : Call<Books>
}