package com.example.bookapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.bookapp.R
import com.example.bookapp.model.Books
import kotlinx.android.synthetic.main.list_item.view.*


class ItemRecyclerViewAdapter internal constructor(context: Context, var books: Books, var onItemClick: OnItemClick)
    : RecyclerView.Adapter<ItemRecyclerViewAdapter.ItemViewHolder>() {

    var inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = inflater.inflate(R.layout.list_item, parent, false)
        return ItemViewHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
//        when (holder) {
//            is ItemViewHolder -> {
//                holder.bind(items.get(position))
//            }
//        }

        val volume = books.items[position].volume
        var authors: String? = volume.authors.toString()

        if (authors == null) {
            authors = ""
        } else {
            authors.replace("[", "")
            authors.replace("]", "")
        }

        holder.titleTextView.text = volume.title
        holder.authorsTextView.text = authors
        holder.dateTextView.text = volume.date
        holder.pagesTextView.text = volume.pages + " pages"

        val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

        var imageLink = try {
            volume.images.image.replace("http", "https")
        } catch (e: Exception) {
            R.drawable.ic_launcher_background
        }

        Glide.with(holder.imageView.context)
                .load(imageLink)
                .apply(requestOptions)
                .into(holder.imageView)


    }


    override fun getItemCount(): Int {
        return books.items.size
    }



    class ItemViewHolder(itemView: View, var onItemClick: OnItemClick): RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val titleTextView: TextView = itemView.titleTextView
        val authorsTextView: TextView = itemView.authorsTextView
        val dateTextView: TextView = itemView.dateTextView
        val pagesTextView: TextView = itemView.pagesTextView
        val imageView: ImageView = itemView.bookImage

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            onItemClick.onItemClicked(adapterPosition)
        }
    }

    interface OnItemClick {
        fun onItemClicked(position: Int)
    }


}