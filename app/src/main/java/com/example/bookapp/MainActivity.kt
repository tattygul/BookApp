package com.example.bookapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }



    fun searchButtonClicked(view: View) {
        if (searchEditText.text.toString().trim().isEmpty()) {
            searchEditText.setText("Type name of book or authors")
        } else {
            val intent = Intent(this, BooksActivity::class.java)
            intent.putExtra("search", searchEditText.text.toString().trim())
            startActivity(intent)
        }
    }
}