package com.example.camerax_gallery.viewholder

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.camerax_gallery.listner.ItemClickListener
import com.example.camerax_gallery.OnetoMany.Album
import com.example.camerax_gallery.R
import kotlinx.android.synthetic.main.photos_list_item_layout.view.*

class ListDirectorViewHolder(private val view : View, private val itemClickListener: ItemClickListener) : RecyclerView.ViewHolder(view) {
//setting the data to the layout with the requited position
    fun setData(album : Album){
        view.apply{
            ivAlbum.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.folder))
            tvAlbumName.text = album.albumName

            //on click of the item passing the albumname
            cardview.setOnClickListener {
                itemClickListener.onItemClicked(album.albumName)
            }
        }
    }
}