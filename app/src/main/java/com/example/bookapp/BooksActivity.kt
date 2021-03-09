package com.example.bookapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookapp.adapters.ItemRecyclerViewAdapter
import com.example.bookapp.model.Books
import com.example.bookapp.network.RetrofitClientInstance
import com.example.bookapp.network.WebService
import kotlinx.android.synthetic.main.activity_books.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class BooksActivity : AppCompatActivity(), ItemRecyclerViewAdapter.OnItemClick {

    var retrofit: Retrofit? = RetrofitClientInstance().getRetrofitInstance()
    var webService: WebService = retrofit!!.create(WebService::class.java)


    lateinit var books: Books

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

        var search: String = intent.getStringExtra("search").toString()

        webService.getBooks(search).enqueue(object : Callback<Books> {
            override fun onResponse(call: Call<Books>, response: Response<Books>) {
                if (response.isSuccessful) {
                    books = response.body()!!
                    initRecyclerView()
                } else {
                    returnBack()
                }
            }

            override fun onFailure(call: Call<Books>, t: Throwable) {

                t.message?.let { Log.e(LOG_TAG, it) }
                Toast.makeText(applicationContext, t.message.toString(), Toast.LENGTH_LONG).show()
            }
        })

        recycler_view.setHasFixedSize(true)


    }

    private fun initRecyclerView() {
        val adapter = ItemRecyclerViewAdapter(applicationContext, books, this)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
        recycler_view.layoutManager = layoutManager
        recycler_view.adapter = adapter
        recycler_view.itemAnimator = DefaultItemAnimator()
    }

    private fun returnBack() {
        val intent = Intent(this, MainActivity::class.java)
        Toast.makeText(applicationContext, "Try again!", Toast.LENGTH_SHORT).show()
        startActivity(intent)

    }

    override fun onItemClicked(position: Int) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(books.items[position].volume.infoLink))
        startActivity(intent)
    }

    companion object {
        private val LOG_TAG = BooksActivity::class.java.simpleName
    }

}