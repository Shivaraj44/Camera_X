package com.example.camerax_gallery.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.camerax_gallery.listner.ItemClickListener
import com.example.camerax_gallery.OnetoMany.Album
import com.example.camerax_gallery.R
import com.example.camerax_gallery.viewholder.ListDirectorViewHolder

class ListDirectoryAdapter(private var albumList : List<Album>,
                           private val itemClickListener: ItemClickListener
) : RecyclerView.Adapter<ListDirectorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListDirectorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.photos_list_item_layout,parent,false)
        return ListDirectorViewHolder(view, itemClickListener)
    }

    override fun onBindViewHolder(holder: ListDirectorViewHolder, position: Int) {
        val album = albumList[position]
        holder.setData(album)
    }

    override fun getItemCount(): Int {
       return albumList.size

    }
}